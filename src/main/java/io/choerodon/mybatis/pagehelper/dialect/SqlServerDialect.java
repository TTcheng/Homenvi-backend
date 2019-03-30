/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2017 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.choerodon.mybatis.pagehelper.dialect;

import java.util.Properties;

import io.choerodon.mybatis.pagehelper.cache.Cache;
import io.choerodon.mybatis.pagehelper.cache.CacheFactory;
import io.choerodon.mybatis.pagehelper.parser.SqlServerParser;
import io.choerodon.mybatis.util.StringUtil;

import me.wcc.base.domain.PageInfo;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;



/**
 * SqlServer分页、count方言类
 *
 * @author liuzh
 */
public class SqlServerDialect extends AbstractHelperDialect {

    protected SqlServerParser sqlServerParser = new SqlServerParser();
    protected Cache<String, String> cacheCountSql;
    protected Cache<String, String> cachePageSql;

    //with(nolock)
    protected String withnolock = ", PAGEWITHNOLOCK";

    @Override
    public String getCountSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey countKey) {
        String sql = boundSql.getSql();
        String cacheSql = cacheCountSql.get(sql);
        if (cacheSql != null) {
            return cacheSql;
        } else {
            cacheSql = sql;
        }
        cacheSql = cacheSql.replaceAll("((?i)with\\s*\\(nolock\\))", withnolock);
        cacheSql = parser.getSmartCountSql(cacheSql);
        cacheSql = cacheSql.replaceAll(withnolock, " with(nolock)");
        cacheCountSql.put(sql, cacheSql);
        return cacheSql;
    }

    @Override
    public String getPageSql(String sql, PageInfo info, CacheKey pageKey) {
        //处理pageKey
        pageKey.update(info.getBegin());
        pageKey.update(info.getSize());
        String cacheSql = cachePageSql.get(sql);
        if (cacheSql == null) {
            cacheSql = sql;
            cacheSql = cacheSql.replaceAll("((?i)with\\s*\\(nolock\\))", withnolock);
            cacheSql = sqlServerParser.convertToPageSql(cacheSql, null, null);
            cacheSql = cacheSql.replaceAll(withnolock, " with(nolock)");
            cachePageSql.put(sql, cacheSql);
        }
        cacheSql = cacheSql.replace(String.valueOf(Long.MIN_VALUE), String.valueOf(info.getBegin()));
        cacheSql = cacheSql.replace(String.valueOf(Long.MAX_VALUE), String.valueOf(info.getSize()));
        return cacheSql;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String sqlCacheClass = properties.getProperty("sqlCacheClass");
        if (StringUtil.isNotEmpty(sqlCacheClass) && !sqlCacheClass.equalsIgnoreCase("false")) {
            cacheCountSql = CacheFactory.createCache(sqlCacheClass, "count", properties);
            cachePageSql = CacheFactory.createCache(sqlCacheClass, "page", properties);
        } else {
            cacheCountSql = CacheFactory.createCache(null, "count", properties);
            cachePageSql = CacheFactory.createCache(null, "page", properties);
        }
    }
}
