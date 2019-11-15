package com.gxuwz.fx.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gxuwz.fx.pojo.ShopOrder;
import com.gxuwz.fx.pojo.WorkerAddress;
import com.gxuwz.fx.pojo.WorkerOrder;
import com.gxuwz.fx.service.ShopOrderService;
import com.gxuwz.fx.service.WorkerAddressService;
import com.gxuwz.fx.service.WorkerOrderService;
import com.gxuwz.fx.service.WorkerService;
import com.gxuwz.fx.utils.RedisUtil;
import com.gxuwz.fx.utils.WebSocketUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

/**
 * 订单处理
 * @author Administrator
 *
 */
@RestController
public class ShopOrderController {
	
	@Autowired ShopOrderService sos;
	
	@Autowired WorkerService ws;
	
	@Autowired WorkerAddressService was;
	
	@Autowired WorkerOrderService wos;
	
	@Autowired WebSocketUtil wsu;
	
	@Autowired RedisUtil ru;
	
	
	/**
	 * 1抢单模式：商家订单接口
	 * @param so
	 * @return
	 */
	@PostMapping("/addorder")
	public String addOrder(@RequestBody ShopOrder so) throws Exception {
		
		Date date = new Date();     
	    long times = date.getTime();//时间戳     
	    System.out.println(times);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
	    String dateString = formatter.format(date);
	    System.out.println(dateString);
	    //json.put("systime",dateString);
	    so.setSystime(dateString); 
	    
	    if(sos.addOrder(so) == 1 ) {     //订单写入数据库
	    	
		   String jsonworker = was.getWorkWa(so.getLongitude(), so.getLatitude());  //查询订单三公里内在接单的工作者
			
		   System.out.println(jsonworker);
		   JSONArray ja = JSONArray.fromObject(jsonworker);
			
				/* 遍历查询到的工作者手机号向移动端发送数据 */
			if(ja.size()>0){   
				
				for(int i=0;i<ja.size();i++){
					// 遍历 jsonarray 数组，把每一个对象转成 json 对象
						
					JSONObject job = ja.getJSONObject(i); 
					
					// 得到 每个对象中的属性值
					double lng2 = (double) job.get("longitude");
					double lat2 = (double) job.get("latitude");
					String phonenum =(String) job.get("phonenum");
			        
			        int  radomid = (int)(Math.random()*1000000000);
			        
			        String id = String.valueOf(radomid);
					
					JSONObject json = JSONObject.fromObject(so);
					
					json.put("wolng", lng2);
					json.put("wolat",lat2);
					json.put("phonenum",phonenum);
			        
			        String jsonorder=json.toString();
			        
			        ru.setattime(id,jsonorder,1800);  //抢单模式：订单进入缓存，30分钟
					
					wsu.sendOrder(phonenum, "订单来啦");
			
			    }
				
					return "success";    //下单成功
				}else {
					return "no";   //下单失败
				}
		}else {
			return "ordernosave";   //订单未写入数据库（保证订单进入数据库保存）
		}
		
		
	}
	
	/**
	 * 商家查询订单
	 * @param keyid
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/shopcxorder/{keyid}")
	public String shopcxOrder(@PathVariable("keyid") String keyid) throws Exception {
		System.out.println(keyid);
		System.out.println(sos.isnorec(keyid));
		if(sos.isnorec(keyid) == true) {
			return "isno";
		}else {
			JSONObject obj = JSONObject.fromObject(wos.shfindWorkerOrderByKey(keyid));
			String jsonorder = obj.toString();
			return jsonorder;
		}
	}
	
	
	/**
	 * 2获取缓存中该工作者的订单
	 * @param so
	 * @return
	 */
	@PostMapping("/getorder/{phonenum}")
	public String getOrder(@PathVariable("phonenum") String phonenum) throws Exception {
		
		if(was.iswork(phonenum) == true) {    //是否为工作状态
		ArrayList<String> list = new ArrayList<String>();  //该手机号的订单列表
		
		//连接本地的 Redis 服务
        @SuppressWarnings("resource")
		Jedis jedis = new Jedis("localhost");
        //System.out.println("连接成功");
 
        // 获取数据并输出
        Set<String> keys = jedis.keys("*");  //获取缓存中所有key
        Iterator<String> it=keys.iterator();  
        
        while(it.hasNext()){             
            String key = it.next();   
            
            if(key.length() < 8) {
            	System.out.println(key.length());
            	ru.delstr(key);
            }else {
           // System.out.println(key); 
            
            String keyvalue = ru.getjson(key);  //获取每个key的value值
           // System.out.println(keyvalue);
            
            JSONObject jsObj = JSONObject.fromObject(keyvalue);   //字符串转为json对象
            jsObj.put("redisid",key);
            
           // System.out.println(jsObj.get("phonenum").toString());
            
            String all = jsObj.toString();
            System.out.println(all);
            if(phonenum.equals(jsObj.get("phonenum").toString())){
            	   list.add(all);
            }
          }
            
        }
           // System.out.println(list);      
        
            JSONArray listArray = JSONArray.fromObject(list);
           // System.out.println(listArray);
            String jsonstr = JSONArray.fromObject(listArray).toString(); 
            return jsonstr;
	}else {    
		    return "isrelax";
	} 
        
	}
	
	/**
	 * 3获取数据库中中该工作者的订单（订单-待配送）（订单-配送中）
	 * @param so
	 * @return
	 */
	@PostMapping("/getdborder/{phonenum}/{sort}")
	public String getDbOrder(@PathVariable("phonenum") String phonenum, @PathVariable("sort") String sort) throws Exception {
		
		if(was.iswork(phonenum) == true) {    //为工作状态
		  	
		  WorkerAddress wa = was.select_wolnglat(phonenum);   //查询该工作者地理位置(传到前台计算距离)
		
		  ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();
		  
		  if(sort.equals("nozhipai")) {     //订单-待配送
			System.out.println("非指派的");
		   list = wos.findWorkerOrderAllByPh(phonenum);  //数据库中该手机号的订单列表
			
			  }else if(sort.equals("zhipai")) { 
				  System.out.println("指派的"); 
				  list = wos.findWorkerOrderAllByPhSo(phonenum); 
			   }
			 
		  JSONArray listArray = JSONArray.fromObject(list);
		  //System.out.println(listArray); 
		  
		  if(listArray.size()>0){
			  for(int i=0;i<listArray.size();i++){
			 // 遍历 jsonarray 数组，把每一个对象转成 json 对象
				JSONObject job = listArray.getJSONObject(i); 
				job.put("wolng",wa.getLongitude());
				job.put("wolat",wa.getLatitude());
			  }
			}
		  
		  String jsonstr = JSONArray.fromObject(listArray).toString();
		  
		  return jsonstr;
		}else {    
		    return "isrelax";
	     } 
        
	}
	
	/**
	 * 4群抢单：工作者查看订单详情（从缓存中）
	 * @param keyid
	 * @return
	 */
	@PostMapping("/findOrderById/{keyid}")
    public String findOrderById(@PathVariable("keyid") String keyid) throws Exception {
        if(ru.iskey(keyid) == true) {     //此订单抢单还没有结束（判断订单是否还存在）
        	//System.out.println(ru.iskey(keyid));
        	new JSONObject();
			JSONObject obj = JSONObject.fromObject(ru.getjson(keyid));   //获得缓存中该订单的数据
			//System.out.println(obj.getInt("status"));
			if(obj.getInt("status") == 0) {    //如果订单还没被抢
	        	return ru.getjson(keyid);   //返回订单数据
	        }else {
	        	return "isrush";  //订单已被抢
	        }
        }else {
        	return "isrush";  
        }
    }
	
	/**
	 * 5群抢单：工作者进行抢单
	 * @param keyid
	 * @return
	 */
	@PostMapping("/rushOrderById/{datastr}")
    public String rushOrderById(@PathVariable("datastr") String datastr) throws Exception {
		// System.out.println(datastr);
		 JSONObject getdata = JSONObject.fromObject(datastr);
		 if(ru.iskey(getdata.getString("redisid")) == true) {    //此订单抢单是否已结束
		        new JSONObject();
				//JSONObject obj = JSONObject.fromObject(ru.getjson(getdata.getString("redisid")));
				//System.out.println(obj.getInt("status"));
				
		        System.out.println(getdata.getString("keyid"));
		        System.out.println(sos.findStatusReByKeyId(getdata.getString("keyid")));
		        
				if(sos.findStatusReByKeyId(getdata.getString("keyid")) == false) {   
				   	     //订单是否已被抢 
					
					sos.updateStatusRe(getdata.getString("keyid"));  //更新订单状态：已被抢
					//System.out.println(getdata.getString("keyid"));
					
					String name = ws.getworkername(getdata.getString("phonenum"));
					
					JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(getdata.getString("keyid")));  //查询此订单数据
					System.out.println (json.toString());
					json.put("status",0);
					json.put("sort",0);
					json.put("phonenum",getdata.getString("phonenum"));   //加入抢单者手机号
				    json.put("name",name);   //加入抢单者姓名
				    
				    Date date = new Date();     
				    long times = date.getTime();//时间戳     
				    System.out.println(times);
				    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
				    String dateString = formatter.format(date);
				    System.out.println(dateString);
				    json.put("systime",dateString);
				    
					WorkerOrder wo = (WorkerOrder)JSONObject.toBean(json,WorkerOrder.class);   //转换成WorkerOrder实体类
					System.out.println(wos.existBykeyid(getdata.getString("keyid"),getdata.getString("phonenum"))); 
					if(wos.existBykeyid(getdata.getString("keyid"),getdata.getString("phonenum")) == false) {
					 wos.addOrder(wo);      //转换成工作者的订单写入数据库
					}else {
				     wos.updateGiOrder(wo);
				   }
					
					String jsonorder=json.toString();  //json对象转换为json字符串
					ru.setattime(getdata.getString("redisid"), jsonorder, 1);  //更新缓存中的订单数据：订单已被抢，5s后自动删除
					return "success";  //返回订单数据给抢到单者，5s后缓存订单自动删除
				}else {
					ru.delstr(getdata.getString("redisid"));
					return "isrush";   //订单已被抢
				}
	        }else {
	        	return "isrush"; //返回时删除这一订单项
	        }
    }
	
	/**
	 * 6抢到单工作者查看订单详情(从数据库查询)
	 * @param keyid
	 * @return
	 */
	@PostMapping("/getOrderById/{keyid}")
    public String getOrderById(@PathVariable("keyid") String keyid) throws Exception {
		
		JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));  //查询此订单数据
		
		String jsonorder=json.toString();  //json对象转换为json字符串
		
		return jsonorder;
    }
	
	/**
	 * 7工作者放弃订单（第二次无人抢、被放弃则指派）
	 * @param keyid
	 * @return
	 */
	@PostMapping("/giveuporder/{phonenum}/{keyid}")
    public String giveuporder(@PathVariable("phonenum") String phonenum , @PathVariable("keyid") String keyid) throws Exception {

	      if(sos.findSortByKeyId(keyid) == false) {
	    	   
	        sos.updateSortByKeyId(keyid);   //sort:0 -> 1
	        sos.updateStatusGi(keyid);      //status:1->0
			wos.updateStatusGi(phonenum,keyid);  //更新工作者订单状态 status:0->2
			
			/* 查询订单信息 */
			JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));
			
			ShopOrder so = (ShopOrder)JSONObject.toBean(json,ShopOrder.class);   //转换成WorkerOrder实体类
	        
			String jsonworker = was.getWorkWa(json.getDouble("longitude"), json.getDouble("latitude"));  //查询订单三公里内在接单的工作者
				
			JSONArray ja = JSONArray.fromObject(jsonworker);
				
					/* 遍历查询到的工作者手机号向移动端发送数据 */
				if(ja.size()>0){   
					
					for(int i=0;i<ja.size();i++){
						// 遍历 jsonarray 数组，把每一个对象转成 json 对象
							
						JSONObject job = ja.getJSONObject(i); 
						// 得到 每个对象中的属性值
						
						double lng2 = (double) job.get("longitude");
						double lat2 = (double) job.get("latitude");
						String phonenum1 =(String) job.get("phonenum");
				        
				        int  radomid = (int)(Math.random()*100000000);
				        
				        String id = String.valueOf(radomid);
						
						JSONObject jsonso = JSONObject.fromObject(so);
	                    System.out.println(jsonso.get("shopname"));
						
						jsonso.put("wolng", lng2);
						jsonso.put("wolat",lat2);
						jsonso.put("phonenum",phonenum1);
				        
				        String jsonorder=jsonso.toString();
				        
				        ru.setattime(id,jsonorder,180);  //返回群抢单缓存：订单进入缓存，3分钟
						
						wsu.sendOrder(phonenum1, "订单来啦");
				
				    }
					
						return "success";    //返回订单缓存成功
					}else {
						return "no";   //返回订单缓存失败
					}
		}else {          //第二次放弃直接指派
			wos.updateStatusGi(phonenum,keyid);  //更新工作者订单状态
			
			JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));   //查询订单商家地理位置
			
			String oneWorker = was.getOneWorkWa(json.getDouble("longitude"), json.getDouble("latitude")); //通过订单位置查询附近最近的一位工作者
			
			JSONObject ja = JSONObject.fromObject(oneWorker); 
	
			String phonenum2 =(String) ja.get("phonenum");
			
			String name = ws.getworkername(phonenum2);
			
			json.put("status",4);
			json.put("sort",1);
			json.put("phonenum",phonenum2);   //加入指派者手机号
		    json.put("name",name);   //加入抢单者姓名
		    
			WorkerOrder wo = (WorkerOrder)JSONObject.toBean(json,WorkerOrder.class);   //转换成WorkerOrder实体类
		
			if(wos.existBykeyid(keyid,phonenum2) == false) {
				 wos.addOrder(wo);      //转换成工作者的订单写入数据库
				}else {
			     wos.updateWoOrder(wo);
			   }
			
		    sos.updateStatusGi(keyid);	
			wsu.sendOrder(phonenum2, "指派单来啦");
			
			return "zhipaisucc";   //指派成功
		}
	
    }
	
	
	/**
	  * 10工作者中转订单
	  * @param phonenum
	  * @return
	  * @throws Exception
	  */
	 @PostMapping("/zzorder/{keyid}")
	 public String zzorder(@RequestBody WorkerAddress wa,@PathVariable("keyid") String keyid) throws Exception {
		     System.out.println(wa.getPhonenum());
			 System.out.println(wa.getLongitude());
			 System.out.println(wa.getLatitude());
			
			 JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));   //查询订单商家信息
			 
			 String oneWorker = was.getOneWorkWa(wa.getLongitude(), wa.getLatitude()); //通过订单位置查询附近最近的一位工作者
			 
			 JSONObject ja = JSONObject.fromObject(oneWorker); 
				
			String phonenum2 =(String) ja.get("phonenum");
			
			String name = ws.getworkername(phonenum2);
			
			json.put("status",4);
			json.put("sort",1);
			json.put("phonenum",phonenum2);   //加入指派者手机号
		    json.put("name",name);   //加入抢单者姓名
		    
			WorkerOrder wo = (WorkerOrder)JSONObject.toBean(json,WorkerOrder.class);   //转换成WorkerOrder实体类
		
			if(wos.existBykeyid(keyid,phonenum2) == false) {
				 wos.addOrder(wo);      //转换成工作者的订单写入数据库
				}else {
			     wos.updateWoOrder(wo);
			   }
			
		    sos.updateStatusGi(keyid);	
			wsu.sendOrder(phonenum2, "指派单来啦");
			
			return "zhipaisucc";   //指派成功
	   }
	
	/**
	 * 8工作者接指派的单
	 * @param phonenum
	 * @return
	 */
	@PostMapping("/acceptorder/{phonenum}/{keyid}")
	public String acceptorder(@PathVariable("phonenum") String phonenum,@PathVariable("keyid") String keyid) throws Exception {
		if(wos.updateStatusToRe(phonenum,keyid) == 1) {
			sos.updateStatusRe(keyid);
			return "success";
		}else {
		    return "failed";	
		}
		
	}
	
	/**
	 * 9工作者开始配送
	 * @param phonenum
	 * @return
	 */
	@PostMapping("/startorder/{phonenum}/{keyid}")
	public String startorder(@PathVariable("phonenum") String phonenum,@PathVariable("keyid") String keyid) throws Exception {
	   if(wos.findworkpsz(phonenum) == false) {	
		if(wos.updateStatusToWo(phonenum, keyid) == 1) {
			return "success";
		}else {
		    return "failed";	
		}
	   }else {
		   return "onlyone";
	   }
		
	}
	

	/**
	 * 
	 * 10工作者获取配送中订单
	 * @param phonenum
	 * @return
	 */
	@PostMapping("/getpszorder/{phonenum}")
	public String getpszorder(@PathVariable("phonenum") String phonenum) throws Exception {
		if(was.iswork(phonenum) == true) {    //为工作状态
		  	
			  WorkerAddress wa = was.select_wolnglat(phonenum);   //查询该工作者地理位置(传到前台计算距离)
			
			  ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();
			  
			  list = wos.findWorkerOrderAllByPhWo(phonenum);  //获取该工作者配送中订单
				 
			  JSONArray listArray = JSONArray.fromObject(list);
			  
			  if(listArray.size()>0){
				  for(int i=0;i<listArray.size();i++){
				 // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					JSONObject job = listArray.getJSONObject(i); 
					job.put("wolng",wa.getLongitude());
					job.put("wolat",wa.getLatitude());
				  }
				}
			  
			  String jsonstr = JSONArray.fromObject(listArray).toString();
			  
			  return jsonstr;
			}else {    
			    return "isrelax";
		     } 
	}
	
	/**
	 * 11工作者完成配送
	 * @param phonenum
	 * @return
	 */
	@PostMapping("/endorder/{phonenum}/{keyid}")
	public String endorder(@PathVariable("phonenum") String phonenum,@PathVariable("keyid") String keyid) throws Exception {
		if(wos.updateStatusRu(phonenum, keyid) == 1) {
			if( sos.updateStatusFi(keyid) == 1) {
				return "success";
			}else {
			    return "failed";	
			}
		}else {
			return "failed";
		}
	}
	
	/**
	 * 12配送中工作者查看订单详情(从数据库查询)
	 * @param keyid
	 * @return
	 */
	@PostMapping("/getPszOrderById/{keyid}/{phonenum}")
    public String getPszOrderById(@PathVariable("keyid") String keyid , @PathVariable("phonenum") String phonenum) throws Exception {
		
		JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));  //查询此订单数据
		
		WorkerAddress wa = was.select_wolnglat(phonenum);
		
		json.put("wolng",wa.getLongitude());
		json.put("wolat",wa.getLatitude());
		json.put("wophonenum",phonenum);
		
		String jsonorder=json.toString();  //json对象转换为json字符串
		
		return jsonorder;
    }
	
	/**
	 * 
	 * 13工作者获取已配送订单
	 * @param phonenum
	 * @return
	 */
	@PostMapping("/getypsorder/{phonenum}")
	public String getypsorder(@PathVariable("phonenum") String phonenum) throws Exception {
		if(was.iswork(phonenum) == true) {    //为工作状态
			
			  ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();
			  
			  list = wos.findWorkerEndOrderAllByPh(phonenum);  //获取该工作者配送中订单
				 
			  JSONArray listArray = JSONArray.fromObject(list);
			  
			  String jsonstr = JSONArray.fromObject(listArray).toString();
			  
			  return jsonstr;
			}else {    
			    return "isrelax";
		     } 
		
	}
	
	/**
	 * 14获取历史订单
	 * @param so
	 * @return
	 */
	@PostMapping("/getlsdd/{phonenum}")
	public String getlsdd(@PathVariable("phonenum") String phonenum) throws Exception {

		  ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();

		  list = wos.findWorkerOrderAllLSDD(phonenum);  //数据库中该手机号的订单列表
		
		  JSONArray listArray = JSONArray.fromObject(list);
		  
		  System.out.println(listArray); 
		  
		  String jsonstr = JSONArray.fromObject(listArray).toString();
		  
		  return jsonstr;
		
        
	}
	
	//Web端
	
	/**
	 * 1获取订单数
	 * @return
	 */
	@PostMapping("/getshopnum")
	public String getshopnum() throws Exception {
		String jsonnum = sos.getallshopnum();
		System.out.println(jsonnum);
		return jsonnum;
	}
	
	/**
	  * 2获取所有已完成订单
	  *
	  * @return
	  * @throws Exception
	  */
	
	  @PostMapping("/web_getallywcshoporder") 
	  public String web_getallywcshoporder() throws Exception { 
		  String jsonallywcshoporder = sos.web_getallywcshoporder();
		  System.out.println(jsonallywcshoporder);
		  JSONArray ja = JSONArray.fromObject(jsonallywcshoporder);
		  if(ja.size() > 0) {
			  return jsonallywcshoporder;
		  }else {
			  return "noywcshoporder";
		  }
     }
	  
  /**
	  * 3获取所有未完成订单
	  *
	  * @return
	  * @throws Exception
	  */
	
	  @PostMapping("/web_getallwwcshoporder") 
	  public String web_getallwwcshoporder() throws Exception { 
		  String jsonallwwcshoporder = sos.web_getallwwcshoporder();
		  System.out.println(jsonallwwcshoporder);
		  JSONArray ja = JSONArray.fromObject(jsonallwwcshoporder);
		  if(ja.size() > 0) {
			  return jsonallwwcshoporder;
		  }else {
			  return "nowwcshoporder";
		  }
     }
	  
  /**
	  * 4获取一个订单
	  *
	  * @return
	  * @throws Exception
	  */
	
	  @PostMapping("/web_getoneshoporder/{keyid}") 
	  public String web_getoneshoporder(@PathVariable("keyid") String keyid ) throws Exception {
		  String jsononeshoporder = sos.web_getoneshoporder(keyid);
		  System.out.println(jsononeshoporder);
		  JSONArray ja = JSONArray.fromObject(jsononeshoporder);
		  if(ja.size() > 0) {
			  return jsononeshoporder;
		  }else {
			  return "nothisshoporder";
		  }
     } 
	  
  /**
	  * 5删除一个订单
	  *
	  * @return
	  * @throws Exception
	  */
	
	  @PostMapping("/web_deleteoneorder/{keyid}") 
	  public String web_deleteoneorder(@PathVariable("keyid") String keyid ) throws Exception {
		  if(sos.web_deleteorder(keyid) == 1) {
		      return "success";
		  }else {
			  return "failed";
		  }
     } 
	
	
	
	
	

}
