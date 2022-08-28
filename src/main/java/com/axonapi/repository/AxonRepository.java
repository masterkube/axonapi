package com.axonapi.repository;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.axonapi.Model.edge;
import com.axonapi.Model.node;

@Repository
public class AxonRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    class edgeRowMapper implements RowMapper <edge> {
        @Override
        public edge mapRow(ResultSet rs, int rowNum) throws SQLException {
           edge e = new edge();
           e.setFrom(rs.getString("from"));
           e.setTo(rs.getString("to"));
           e.setLabel(rs.getString("label"));
           return e;
        }
    }

    class nodeRowMapper implements RowMapper <node> {
        @Override
        public node mapRow(ResultSet rs, int rowNum) throws SQLException {
           node n = new node();
           n.setId(rs.getString("ProcessId"));
           n.setLabel(rs.getString("ProcessId"));
           n.setOutbounds(rs.getString("OutBounds"));
           n.setInbounds(rs.getString("InBounds"));
           return n;
        }
    }

    public String test(){
        return "Hello World";
    }
    
    public List<edge> getallrelation(){
        try{
            String sql1 = "select distinct ProcessId as 'from', SignalProcessId as 'to' ,SignalActionName as 'label' from payloadsignalnamestore where  ProcessName != SignalProcessName;";

            List<edge> edgeResult = jdbcTemplate.query(sql1,new edgeRowMapper());
            return edgeResult;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public List<node> getnodes(){
        try{
            String sql2 = "select ps.ProcessId as 'ProcessId', GROUP_CONCAT(distinct it.ActionName) as 'OutBounds', GROUP_CONCAT(distinct ia.ActionName) as 'InBounds' from processstore ps, interactionspacetell it, interactionspaceask ia where it.IsNameInternal = 0 and ia.IsNameInternal = 0 GROUP by ps.ProcessId";
        
            List<node> nodes = jdbcTemplate.query(sql2, new nodeRowMapper());
            return nodes;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
