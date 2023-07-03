package com.axonapi.repository;

import java.util.List;
import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.axonapi.Model.Category;
import com.axonapi.Model.Shipment;
import com.axonapi.Model.SubCategory;
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
           return n;
        }
    }

    class latlongRowMapper implements RowMapper <latlong> {
        @Override
        public latlong mapRow(ResultSet rs, int rowNum) throws SQLException {
           latlong l = new latlong();
           l.setProcessID(rs.getString("ProcessID"));
           l.setAvatarName(rs.getString("AvatarName"));
           l.setLatitude(rs.getString("latitude"));
           l.setLongitude(rs.getString("longitude"));
           return l;
        }
    }

    class shipmentRowMapper implements RowMapper <Shipment> {
        @Override
        public Shipment mapRow(ResultSet rs, int rowNum) throws SQLException {
           Shipment s = new Shipment();
           s.setProcessId(rs.getString("ProcessId"));
           s.setAvatarName(rs.getString("AvatarName"));
           return s;
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
            String sql2 = "select ps.ProcessId from processstore ps, interactionspacetell it, interactionspaceask ia, avatarnamestore an where an.ProcessId = ps.ProcessId and it.IsNameInternal = 0 and ia.IsNameInternal = 0 GROUP by ps.ProcessId;";
            List<node> nodes = jdbcTemplate.query(sql2, new nodeRowMapper());
            return nodes;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public List<latlong> getalldsn(){
        try{
            String sql3 = "select an.AvatarName as 'AvatarName', pfs.ProcessID as 'ProcessID', TRIM(SUBSTRING_INDEX(pfs.DataElementValue,',',1)) as latitude, TRIM(SUBSTRING_INDEX(pfs.DataElementValue,',',-1)) as longitude from payloadfilestore pfs, avatarnamestore an where DataElementName in (select ElementName from elementsrepository where ElementType='latlong') and an.ProcessID = pfs.ProcessID and an.AvatarName != pfs.ProcessID;";
            List<latlong> latlongdata = jdbcTemplate.query(sql3, new latlongRowMapper() );
            return latlongdata;
        }catch(Exception e){
            return null;
        }
    }

    public List<Shipment> getallshipment(String name){
        try{
            String sql4 = "select ps.ProcessId as 'ProcessId', an.AvatarName as 'AvatarName' from processstore ps, interactionspacetell it, interactionspaceask ia, avatarnamestore an where an.ProcessId = ps.ProcessId and it.IsNameInternal = 0 and ia.IsNameInternal = 0 GROUP by ps.ProcessId having ps.ProcessName='"+name+"';";
            System.out.println(name+" "+sql4);
            List<Shipment> shipments = jdbcTemplate.query(sql4, new shipmentRowMapper() );
            return shipments;
        }catch(Exception e){
            return null;
        }
    }
}
