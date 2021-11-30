//package com.pet.utils;
//
//import org.springframework.data.redis.connection.DataType;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class RedisUtil {
//
//    private StringRedisTemplate redisTemplate;
//
//    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    public StringRedisTemplate getRedisTemplate() {
//        return this.redisTemplate;
//    }
//
//    /** -------------------key相关操作--------------------- */
//
//    /**
//     * 删除key
//     *
//     * @param key
//     */
//    public void deleteKey(String key) {
//        redisTemplate.delete(key);
//    }
//
//    /**
//     * 批量删除key
//     *
//     * @param keys
//     */
//    public void deleteKeys(Collection<String> keys) {
//        redisTemplate.delete(keys);
//    }
//
//    /**
//     * 序列化key
//     *
//     * @param key
//     * @return
//     */
//    public byte[] dumpKey(String key) {
//        return redisTemplate.dump(key);
//    }
//
//    /**
//     * 是否存在key
//     *
//     * @param key
//     * @return
//     */
//    public Boolean hasKey(String key) {
//        return redisTemplate.hasKey(key);
//    }
//
//    /**
//     * 设置过期时间
//     *
//     * @param key
//     * @param timeout
//     * @param unit
//     * @return
//     */
//    public Boolean expire(String key, long timeout, TimeUnit unit) {
//        return redisTemplate.expire(key, timeout, unit);
//    }
//
//    /**
//     * 设置过期时间
//     *
//     * @param key
//     * @param date
//     * @return
//     */
//    public Boolean expireAt(String key, Date date) {
//        return redisTemplate.expireAt(key, date);
//    }
//
//    /**
//     * 查找匹配的key
//     *
//     * @param pattern
//     * @return
//     */
//    public Set<String> findMatchKeys(String pattern) {
//        return redisTemplate.keys(pattern);
//    }
//
//    /**
//     * 移除 key 的过期时间，key 将持久保持
//     *
//     * @param key
//     * @return
//     */
//    public Boolean persist(String key) {
//        return redisTemplate.persist(key);
//    }
//
//    /**
//     * 返回 key 的剩余的过期时间
//     *
//     * @param key
//     * @return
//     */
//    public Long getExpire(String key) {
//        return redisTemplate.getExpire(key);
//    }
//
//    /**
//     * 修改 key 的名称
//     *
//     * @param oldKey
//     * @param newKey
//     */
//    public void modifyKeyName(String oldKey, String newKey) {
//        redisTemplate.rename(oldKey, newKey);
//    }
//
//    /**
//     * 仅当 newKey 不存在时，将 oldKey 改名为 newKey
//     *
//     * @param oldKey
//     * @param newKey
//     * @return
//     */
//    public Boolean renameIfAbsent(String oldKey, String newKey) {
//        return redisTemplate.renameIfAbsent(oldKey, newKey);
//    }
//
//    /**
//     * 返回 key 所储存的值的类型
//     *
//     * @param key
//     * @return
//     */
//    public DataType keyType(String key) {
//        return redisTemplate.type(key);
//    }
//
//    /** -------------------string相关操作--------------------- */
//
//    /**
//     * 设置指定 key 的值
//     * @param key
//     * @param value
//     */
//    public void setKey(String key, String value) {
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    /**
//     * 获取指定 key 的值
//     * @param key
//     * @return
//     */
//    public String getKey(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//    /**
//     * 批量获取
//     *
//     * @param keys
//     * @return
//     */
//    public List<String> multiGet(Collection<String> keys) {
//        return redisTemplate.opsForValue().multiGet(keys);
//    }
//    /**
//     * 获取字符串的长度
//     *
//     * @param key
//     * @return
//     */
//    public Long keySize(String key) {
//        return redisTemplate.opsForValue().size(key);
//    }
//
//    /**
//     * 批量添加
//     *
//     * @param maps
//     */
//    public void multiSet(Map<String, String> maps) {
//        redisTemplate.opsForValue().multiSet(maps);
//    }
//
//    /**
//     * 追加到末尾
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Integer append(String key, String value) {
//        return redisTemplate.opsForValue().append(key, value);
//    }
//
//    /** -------------------hash相关操作------------------------- */
//
//    /**
//     * 获取存储在哈希表中指定字段的值
//     *
//     * @param key
//     * @param field
//     * @return
//     */
//    public Object hashGet(String key, String field) {
//        return redisTemplate.opsForHash().get(key, field);
//    }
//
//    /**
//     * 获取所有给定字段的值
//     *
//     * @param key
//     * @return
//     */
//    public Map<Object, Object> hashGetAll(String key) {
//        return redisTemplate.opsForHash().entries(key);
//    }
//
//    /**
//     * 获取所有给定字段的值
//     *
//     * @param key
//     * @param fields
//     * @return
//     */
//    public List<Object> hashMultiGet(String key, Collection<Object> fields) {
//        return redisTemplate.opsForHash().multiGet(key, fields);
//    }
//
//    public void hashPut(String key, String hashKey, String value) {
//        redisTemplate.opsForHash().put(key, hashKey, value);
//    }
//
//    public void hashPutAll(String key, Map<String, String> maps) {
//        redisTemplate.opsForHash().putAll(key, maps);
//    }
//
//    /**
//     * 删除一个或多个哈希表字段
//     *
//     * @param key
//     * @param fields
//     * @return
//     */
//    public Long hDelete(String key, Object... fields) {
//        return redisTemplate.opsForHash().delete(key, fields);
//    }
//
//    /**
//     * 查看哈希表 key 中，指定的字段是否存在
//     *
//     * @param key
//     * @param field
//     * @return
//     */
//    public boolean hExists(String key, String field) {
//        return redisTemplate.opsForHash().hasKey(key, field);
//    }
//
//    /**
//     * 获取所有哈希表中的字段
//     *
//     * @param key
//     * @return
//     */
//    public Set<Object> hKeys(String key) {
//        return redisTemplate.opsForHash().keys(key);
//    }
//
//    /**
//     * 获取哈希表中字段的数量
//     *
//     * @param key
//     * @return
//     */
//    public Long hSize(String key) {
//        return redisTemplate.opsForHash().size(key);
//    }
//
//    /**
//     * 获取哈希表中所有值
//     *
//     * @param key
//     * @return
//     */
//    public List<Object> hValues(String key) {
//        return redisTemplate.opsForHash().values(key);
//    }
//
//    /** ------------------------list相关操作---------------------------- */
//
//    /**
//     * 通过索引获取列表中的元素
//     *
//     * @param key
//     * @param index
//     * @return
//     */
//    public String listIndex(String key, long index) {
//        return redisTemplate.opsForList().index(key, index);
//    }
//
//    /**
//     * 获取列表指定范围内的元素
//     *
//     * @param key
//     * @param start
//     *            开始位置, 0是开始位置
//     * @param end
//     *            结束位置, -1返回所有
//     * @return
//     */
//    public List<String> listRange(String key, long start, long end) {
//        return redisTemplate.opsForList().range(key, start, end);
//    }
//
//    /**
//     * 通过索引设置列表元素的值
//     *
//     * @param key
//     * @param index
//     *            位置
//     * @param value
//     */
//    public void listSet(String key, long index, String value) {
//        redisTemplate.opsForList().set(key, index, value);
//    }
//
//    /**
//     * 移出并获取列表的第一个元素
//     *
//     * @param key
//     * @return 删除的元素
//     */
//    public String listLeftPop(String key) {
//        return redisTemplate.opsForList().leftPop(key);
//    }
//
//    /**
//     * 移除并获取列表最后一个元素
//     *
//     * @param key
//     * @return 删除的元素
//     */
//    public String listRightPop(String key) {
//        return redisTemplate.opsForList().rightPop(key);
//    }
//
//    /**
//     * 裁剪list
//     *
//     * @param key
//     * @param start
//     * @param end
//     */
//    public void lTrim(String key, long start, long end) {
//        redisTemplate.opsForList().trim(key, start, end);
//    }
//
//    /**
//     * 获取列表长度
//     *
//     * @param key
//     * @return
//     */
//    public Long listLen(String key) {
//        return redisTemplate.opsForList().size(key);
//    }
//
//    /** --------------------set相关操作-------------------------- */
//
//    /**
//     * set添加元素
//     *
//     * @param key
//     * @param values
//     * @return
//     */
//    public Long sAdd(String key, String... values) {
//        return redisTemplate.opsForSet().add(key, values);
//    }
//
//    /**
//     * set移除元素
//     *
//     * @param key
//     * @param values
//     * @return
//     */
//    public Long sRemove(String key, Object... values) {
//        return redisTemplate.opsForSet().remove(key, values);
//    }
//
//    /**
//     * 移除并返回集合的一个随机元素
//     *
//     * @param key
//     * @return
//     */
//    public String sPop(String key) {
//        return redisTemplate.opsForSet().pop(key);
//    }
//
//    /**
//     * 获取集合的大小
//     *
//     * @param key
//     * @return
//     */
//    public Long sSize(String key) {
//        return redisTemplate.opsForSet().size(key);
//    }
//
//    /**
//     * 判断集合是否包含value
//     *
//     * @param key
//     * @param value
//     * @return
//     */
//    public Boolean sIsMember(String key, Object value) {
//        return redisTemplate.opsForSet().isMember(key, value);
//    }
//
//    /**
//     * 获取两个集合的交集
//     *
//     * @param key
//     * @param otherKey
//     * @return
//     */
//    public Set<String> sIntersect(String key, String otherKey) {
//        return redisTemplate.opsForSet().intersect(key, otherKey);
//    }
//
//    /**
//     * 获取两个集合的并集
//     *
//     * @param key
//     * @param otherKeys
//     * @return
//     */
//    public Set<String> sUnion(String key, String otherKeys) {
//        return redisTemplate.opsForSet().union(key, otherKeys);
//    }
//
//    /**
//     * 获取key集合与多个集合的并集
//     *
//     * @param key
//     * @param otherKeys
//     * @return
//     */
//    public Set<String> sUnion(String key, Collection<String> otherKeys) {
//        return redisTemplate.opsForSet().union(key, otherKeys);
//    }
//
//    /**
//     * 获取两个集合的差集
//     *
//     * @param key
//     * @param otherKey
//     * @return
//     */
//    public Set<String> sDifference(String key, String otherKey) {
//        return redisTemplate.opsForSet().difference(key, otherKey);
//    }
//
//    /**
//     * 获取集合所有元素
//     *
//     * @param key
//     * @return
//     */
//    public Set<String> setMembers(String key) {
//        return redisTemplate.opsForSet().members(key);
//    }
//
//    /**
//     * 随机获取集合中的一个元素
//     *
//     * @param key
//     * @return
//     */
//    public String sRandomMember(String key) {
//        return redisTemplate.opsForSet().randomMember(key);
//    }
//
//}
