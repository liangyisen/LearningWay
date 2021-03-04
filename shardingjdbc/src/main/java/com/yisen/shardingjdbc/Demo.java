package com.yisen.shardingjdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.json.JSONUtils;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Demo {
    public static void main(String[] args) throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
// 配置第1个数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        String ip = "112.125.80.159"; //9

        dataSource1.setUrl("jdbc:mysql://" + ip + ":3306/shardingjdbcDB0");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        dataSourceMap.put("shardingjdbcDB0", dataSource1);
// 配置第2个数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://" + ip + ":3306/shardingjdbcDB1");
        dataSource2.setUsername("root");
        dataSource2.setPassword("root");
        dataSourceMap.put("shardingjdbcDB1", dataSource2);


        // 配置Order表规则
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("t_order", "shardingjdbcDB${0..1}.t_order_${0..1}");

        // 配置分库策略（Groovy表达式配置db规则）
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "shardingjdbcDB${user_id % 2}"));
        // 配置分表策略（Groovy表达式配置表路由规则）
        tableRuleConfiguration.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, new Properties());
        String sql = "insert into t_order (user_id, order_id) values (?, ?)";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 33);
        preparedStatement.setInt(2, 11);
        preparedStatement.execute();

    }
}