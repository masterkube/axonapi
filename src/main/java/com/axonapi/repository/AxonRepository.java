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
import com.axonapi.Model.latlong;
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
           n.setLabel(rs.getString("AvatarName"));
           n.setParentnode(rs.getString("ProcessName"));
           return n;
        }
    }

    class latlongRowMapper implements RowMapper <latlong> {
        @Override
        public latlong mapRow(ResultSet rs, int rowNum) throws SQLException {
           latlong l = new latlong();
           l.setProcessID(rs.getString("ProcessID"));
           l.setFrom(rs.getString("from"));
           l.setTo(rs.getString("to"));
           l.setLabel(rs.getString("label"));
           l.setLattitude(rs.getString("latitude"));
           l.setLongitude(rs.getString("longitude"));
           return l;
        }
    }

    public String test(){
        return "Hello World";
    }
    
    public List<edge> getallrelation(){
        try{
            String sql1 = "select distinct ProcessId as 'from', SignalProcessId as 'to' ,SignalActionName as 'label' from payloadsignalnamestore where  ProcessId != SignalProcessId;";

            List<edge> edgeResult = jdbcTemplate.query(sql1,new edgeRowMapper());
            return edgeResult;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public List<node> getnodes(){
        try{
            //String sql2 = "select ps.ProcessId as 'ProcessId', GROUP_CONCAT(distinct it.ActionName) as 'OutBounds', GROUP_CONCAT(distinct ia.ActionName) as 'InBounds' from processstore ps, interactionspacetell it, interactionspaceask ia where it.IsNameInternal = 0 and ia.IsNameInternal = 0 GROUP by ps.ProcessId";
            //with avatar name for label
            String sql2 = "select ps.ProcessId, an.AvatarName, ps.ProcessName from processstore ps, interactionspacetell it, interactionspaceask ia, avatarnamestore an where an.ProcessId = ps.ProcessId and it.IsNameInternal = 0 and ia.IsNameInternal = 0 GROUP by ps.ProcessId;";
            List<node> nodes = jdbcTemplate.query(sql2, new nodeRowMapper());
            return nodes;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public List<latlong> getalldsn(){
        try{
            String sql3 = "select pfs.ProcessID,SUBSTRING_INDEX(pfs.DataElementValue,',',1) as latitude, SUBSTRING_INDEX(pfs.DataElementValue,',',-1) as longitude, psn.ProcessId as 'from', psn.SignalProcessId as 'to', psn.SignalActionName as label from payloadfilestore pfs, payloadsignalnamestore psn where pfs.DataElementName in (select ElementName from elementsrepository where ElementType='latlong') and psn.ProcessId in (pfs.ProcessID);";
            List<latlong> latlongdata = jdbcTemplate.query(sql3, new latlongRowMapper() );
            return latlongdata;
        }catch(Exception e){
            return null;
        }
    }
}
