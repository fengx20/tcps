package com.gxuwz.fx.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Redis配置类
 * 自定义RedisTemplate，
 * 原因：
 * RedisAutoConfiguration提供的RedisTemplate没有实现我们所需要的序列化；
 * 泛型总是<Object, Object>，大部分场景我们更需要<String, Object>。
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
    
	@Bean
    public RedisTemplate<String, Object> redisTemplateCustomize(RedisConnectionFactory factory) {
	    System.out.println("。。。。。。。。。。。。如果我输出了，说明自定义的redisTemplate实例化了。。。。。。。。。。。。。。。。");
    	RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    	template.setConnectionFactory(factory);
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
