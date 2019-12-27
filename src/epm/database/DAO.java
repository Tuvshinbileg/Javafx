package epm.database;

import epm.models.*;
import epm.models.employee.EmployeeDetail;
import epm.models.employee.SimpleEmp;
import epm.models.salary.AllowanceDeduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection connection;
    private final String dbUrl = "jdbc:sqlite:";
    private final String dbName = "EPM.db";
    private static DAO instance=null;
    private DAO() {
        if (connection == null) {
            this.connection = SQLITEConnection.getConnection(this.dbUrl, this.dbName);

        } else {
            System.out.println("Connected");
        }
    }
    public static DAO getObjectRefrence(){
        if(instance==null){
            instance=new DAO();
        }
        return instance;
    }
    public List<Department> selectDepartment() throws SQLException {
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="SELECT *from Department ";
        List<Department> listDepartment=new ArrayList<>();
        try {
            ps=this.connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                listDepartment.add(new Department(rs.getString("department")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(ps!=null)
                ps.close();
            if(rs!=null)
                rs.close();
        }
        return listDepartment;
    }

    public String selectOvertime(String empid){
        PreparedStatement pst=null;
        ResultSet rs=null;
        String hours="";
        String sql="SELECT SUM(whours) as whours from OverTime WHERE empid=? ";
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,empid);
            rs=pst.executeQuery();
            while (rs.next()){
                hours=rs.getString("whours");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  hours;
    }

    public String selectWhoursPresent(String fromdate,String todate,String empcode ) {
        System.out.println("WHOURS");
        PreparedStatement pst = null;
        ResultSet rs = null;
        String whours="";
        String sql = " SELECT SUM(whours) FROM Present where empid=? and edate >=? AND edate<=? ";
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,empcode);
            pst.setString(2,fromdate);
            pst.setString(3,todate);
            rs=pst.executeQuery();
            rs.next();
            whours=rs.getString(1);
            System.out.println(whours);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  whours;
    }

    public List<AdminDesignation> selectDesignation() throws SQLException {
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="SELECT *from Designation";
        List<AdminDesignation> listDesignation=new ArrayList<>();
        try {
            ps=this.connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                listDesignation.add(new AdminDesignation(rs.getString("designation"),rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(ps!=null)
                ps.close();
            if(rs!=null)
                rs.close();
        }
        return  listDesignation;

    }
    public List<String> selectAllEmpid() throws SQLException {
        List<String> list=new ArrayList<>();
        String sql="SELECT empcode FROM EmployeeInfo";
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            pst=this.connection.prepareStatement(sql);
            rs=pst.executeQuery();
            while (rs.next()){
                list.add(rs.getString("empcode"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(pst!=null){
                pst.close();
            }
            if(rs!=null){
                rs.close();
            }
        }
        return  list;
    }
    public SimpleEmp selectByEmpid(String empid){
        PreparedStatement pst=null;
        ResultSet rs = null;
        String sql="SELECT *FROM EmployeeInfo where empcode=?";
        SimpleEmp simpleEmp=null;
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,empid);
            rs=pst.executeQuery();
        while (rs.next()){
            simpleEmp=new SimpleEmp(rs.getString("firstname"),rs.getString("hourlySalary"),rs.getString("department"),rs.getString("designation"));
            System.out.println(simpleEmp.toString());
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return simpleEmp;
    }
    public  List<ReportAdminAttendance> selectPresent(String fromDate, String toDate) throws SQLException {
        List<ReportAdminAttendance> list=new ArrayList<>();
            PreparedStatement ps=null;
            ResultSet rs=null;
            System.out.println(fromDate +", " +toDate);
            String sql= "SELECT *FROM Present where edate >=? AND edate<=?";
                    ps=this.connection.prepareStatement(sql);
                    ps.setString(1,fromDate);
                    ps.setString(2,toDate);
                    rs=ps.executeQuery();

                    while (rs.next()){
                     list.add(new ReportAdminAttendance(rs.getString("empid"),rs.getString("edate")
                     ,rs.getString("etime"),rs.getString("extime"),rs.getString("whours")));
                    }
                    ps.close();
                    rs.close();
        return list;
    }
    public List<Payslip> selectPayslip(String fromDate,String toDate){
        List<Payslip> list=new ArrayList<>();
        System.out.println("DATE");
       PreparedStatement pst=null;
       ResultSet rs=null;

        String sql= "SELECT *FROM Payslip where Paydate >=? AND Paydate<=?";
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,fromDate);
            pst.setString(2,toDate);
            rs=pst.executeQuery();
            while (rs.next()){
                System.out.println("TRU");
                //Slno, Empid,Name,Department,Designation,LossOfPay,prepareStatementNetsal,Paydate
                list.add(new Payslip(rs.getString("Slno"),rs.getString("Empid"),rs.getString("Name"),rs.getString("Department")
                ,rs.getString("Designation"),rs.getString("LossOfPay"),rs.getString("Netsal"),rs.getString("Paydate")));

            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;
    }
    public User isLogin(String uname, String upass) throws Exception {
        PreparedStatement ps= null;
        ResultSet rs = null;
        System.out.println(uname +", " +upass);
        String sql = "SELECT  empcode,firstname,usertype  from EmployeeInfo where  username=? and password=? ";
        User usr=null;
        try {
            ps = this.connection.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2,upass);
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(usr);
                usr=new User(rs.getString("firstname"),rs.getString("usertype"),rs.getString("empcode"));

            }

        } catch (SQLException e) {
        }
        finally {
            if(ps!=null){
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }
            if(this.connection!=null){
            }
            return usr;
        }
    }
    public MyAccount selectMyAcc(String empcode) throws Exception {
        System.out.println("Empcode:"+empcode);
        PreparedStatement ps= null;
        ResultSet rs = null;
        MyAccount myAcc=null;
        String sql = "SELECT firstname,lastname,gender,dateOfbirth,address,city,contactNo,bank_acc_no,emailid  from EmployeeInfo where  empcode=? ";
        try {
            ps = this.connection.prepareStatement(sql);
            ps.setString(1, empcode);
            rs = ps.executeQuery();
            while (rs.next()){
            myAcc=new MyAccount(rs.getString("firstname"),rs.getString("lastname"),rs.getString("gender"),rs.getString("dateOfbirth"),rs.getString("address"),rs.getString("city"),
                    rs.getString("contactno"),rs.getString("bank_acc_no"),rs.getString("emailid"));

            }
            System.out.println(myAcc);

        } catch (SQLException e) {
        }
        finally {
            if(ps!=null){
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }

            return myAcc;
        }
    }
    public EmployeeDetail searchByDepartment(String department){
        String sql="SELECT * from EmployeeInfo where department= ?  ";
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            pst=this.connection.prepareStatement(sql);
            rs=pst.executeQuery();
            while (rs.next()){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean insertIntoDesignation(String description,String designation) throws SQLException {
        String query="INSERT INTO Designation (designation, description)  VALUES(?,?)";
            PreparedStatement ps=null;
            int rs=0;
        try {
            ps=this.connection.prepareStatement(query);
            ps.setString(1,designation);
            ps.setString(2,description);
            rs=ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(ps!=null){
                ps.close();
            }
            if(rs>0){
                return true;
            }
        }
        return false;
    }
    public boolean insertIntoDepartment(String departmant) throws SQLException {
        String query="INSERT INTO Department (department)  VALUES(?)";
        PreparedStatement ps=null;
        int rs=0;
        try {
            ps=this.connection.prepareStatement(query);
            ps.setString(1,departmant);
            rs=ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(ps!=null){
                ps.close();
            }
            if(rs>0){
                return true;
            }
        }
        return false;
    }
    public boolean insertIntoEmployee(EmployeeDetail emp) throws SQLException {
        String sql="INSERT INTO EmployeeInfo (empcode,lastName,firstName,gender,address,contactno,dateofbirth,city,emailid,department,designation,hourlySalary,doj,bank_acc_no,pincode,password,usertype,username)" +
                "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst=null;
        int rs=0;
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,emp.getEmpcode());
            pst.setString(2,emp.getFirstname());
            pst.setString(3,emp.getLastname());
            pst.setString(4,emp.getGender());
            pst.setString(5,emp.getAddress());
            pst.setString(6,emp.getContactno());
            pst.setString(7,emp.getDob());
            pst.setString(8,emp.getCity());
            pst.setString(9,emp.getEmaildid());
            pst.setString(10,emp.getDepartment());
            pst.setString(11,emp.getDesignation());
            pst.setString(12,emp.getHourlySalary());
            pst.setString(13,emp.getDoj());
            pst.setString(14,emp.getBankAccount());
            pst.setString(15,emp.getPincode());
            pst.setString(16,emp.getPassword());
            pst.setString(17,emp.getUsertype());
            pst.setString(18,emp.getUsername());
            rs=pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(pst!=null){
                pst.close();
            }
            if(rs>0){
                return true;
            }
        }
         return false;
    }
    public boolean insertIntoOverTime(String empID,String date,int hours) throws SQLException {
       String sql="INSERT INTO OverTime (empid,date, whours) VALUES(?,?,?)";
        PreparedStatement pst=null;
        int rs=0;
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,empID);
            pst.setString(2,date);
            pst.setInt(3,hours);
            rs=pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(pst!=null) pst.close();
        }
        if(rs>0){
            return true;
        }
        return false;
    }
    public boolean insertIntoAllowance(AllowanceDeduct allowanceDeduct){
        PreparedStatement pst=null;
        int rs=0;

        return false;
    }
    public boolean insertEntryTime(String empId,String date,String time) throws SQLException {
        String sql="INSERT INTO Present (empid,edate,etime) VALUES(?,?,?)";
        PreparedStatement pst=null;
            int rs=0;
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,empId);
            pst.setString(2,date);
            pst.setString(3,time);
            rs=pst.executeUpdate();
            pst.close();
            if(rs>0){
                return  true;
            }

        return false;
    }
    public boolean updateExitTime(String empId, String date,String time){
        String sql="UPDATE Present  SET extime=?, status=1 WHERE empid=? and  edate=?";
       // String sql1="SELECT a.extime - a.etime AS Difference FROM Present a";
        PreparedStatement pst=null;
        int rs=0;
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,time);
            pst.setString(2,empId);
            pst.setString(3,date);
            rs=pst.executeUpdate();
//
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(rs>0){
            new Thread( ()->{
                try {
                    inserIntoWhours(empId,date,time);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).start();

            return  true;
        }

        return false;
    }
    public void inserIntoWhours(String empId, String date,String time) throws SQLException {
        String diff=selectDiffrence(empId,date,time);
        PreparedStatement pst=null;
        String sql="UPDATE Present SET whours=? where empid=? and extime=? and edate=?";
        pst=this.connection.prepareStatement(sql);
        pst.setString(1,diff);
        pst.setString(2,empId);
        pst.setString(3,time);
        pst.setString(4,date);
        int r=pst.executeUpdate();
        if(r>0){
            System.out.println("Succesfully updated WHours");
        }
        if(pst!=null){
            pst.close();
        }


    }
    public String selectDiffrence(String empId, String date,String time) throws SQLException {
        String diff="";
        PreparedStatement pst=null;
        ResultSet rs=null;
        String sql= "SELECT a.extime - a.etime AS Difference FROM Present a WHERE edate=? and empid=?";
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,date);
            pst.setString(2,empId);
            rs=pst.executeQuery();
            rs.next();
            diff=rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(pst!=null)
            pst.close();
            if (rs!=null)
            rs.close();
        }
        return diff;
    }

    public boolean insertIntoEarnedLeave(String empid,String edate){
        PreparedStatement pst=null;
        int res=0;
        String sql="INSERT INTO EarnedLeave (empid,edate) VALUES(?,?)";
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,empid);
            pst.setString(2,edate);
            res=pst.executeUpdate();
            if(res>0){
                return  true;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }
    public String selectEtime(String date,String empid){
        String sql="SELECT etime from Present WHERE empid=? AND edate=?";
        PreparedStatement pst=null;
        ResultSet resultSet=null;
        String etime="";
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,empid);
            pst.setString(2,date);

            resultSet=pst.executeQuery();
            while (resultSet.next()){
                etime=resultSet.getString("etime");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;


    }
    public List<EventManage> selectEvent() throws SQLException {
        List<EventManage> list=new ArrayList<>();
        PreparedStatement pst=null;
        ResultSet res=null;
        String sql="SELECT * from Event";
        pst=this.connection.prepareStatement(sql);
        res=pst.executeQuery();
        String fromdate,todate,fromtime,toTime, eventName,eventDescription;
       while (res.next()){
           list.add(new EventManage(res.getString("Fromdate"),res.getString("Todate"),res.getString("Fromtime"),res.getString("Totime"),res.getString("Event"),res.getString("Description")));
       }

       pst.close();
       res.close();
        return list;
    }
    public boolean insertManageLeave(ManageLeave manageLeave) throws SQLException {

        PreparedStatement pst=null;
        int res=0;
        String sql="INSERT INTO ManageLeave (empid,leavedDate,totalLeave,remainingLeave,reason) VALUES(?,?,?,?,?)";
        pst=this.connection.prepareStatement(sql);
        pst.setString(1,manageLeave.getEmpid());
        pst.setString(2,manageLeave.getLeavedate());
        pst.setString(3,manageLeave.getTotalLeave());
        pst.setString(4,manageLeave.getRemainingLeave());
        pst.setString(5,manageLeave.getReason());
        res=pst.executeUpdate();
        pst.close();
        if(res>0){
            return true;
        }
        return false;
    }


    public boolean insertIntoEvent(EventManage event){
        PreparedStatement pst=null;
        int res=0;
        String sql="INSERT INTO Event(Event,Description,Fromdate,Todate,Fromtime,Totime) VALUES(?,?,?,?,?,?)";
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,event.getEventName());
            pst.setString(2,event.getEventDescription());
            pst.setString(3,event.getFromdate());
            pst.setString(4,event.getTodate());
            pst.setString(5,event.getFromtime());
            pst.setString(6,event.getToTime());
            res=pst.executeUpdate();

            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(res>0){
                return true;
            }
        }
        return false;
    }
    public String selectManageLeave(String empid) throws SQLException {
        PreparedStatement pst=null;
        ResultSet rs=null;
        String sum="";
        String sql="SELECT totalLeave+remainingLeave as sum from ManageLeave where empid=?";
        pst=this.connection.prepareStatement(sql);
        pst.setString(1,empid);
        rs=pst.executeQuery();
        rs.next();
        sum=rs.getString(1);
        pst.close();
        rs.close();
        return sum;
    }
    public boolean insertIntoPayslip(Payslip payslip){
        PreparedStatement pst=null;
        int res=0;
        String sql="INSERT INTO Payslip (Empid,Name,Department,Designation,LossOfPay,Netsal,Paydate) VALUES(?,?,?,?,?,?,?)";
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,payslip.getEmpid());
            pst.setString(2, payslip.getName());
            pst.setString(3,payslip.getDepartment());
            pst.setString(4,payslip.getDesignation());
            pst.setString(5,payslip.getLossOfpay());
            pst.setString(6,payslip.getNetsal());
            pst.setString(7,payslip.getPaydate());
            res=pst.executeUpdate();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(res>0){
            return true;
        }
        return false;
    }
    public boolean updateEmpAcc(UpdatingAcc acc,String empcode){
        PreparedStatement pst=null;
        int res=0;
        String sql="UPDATE EmployeeInfo SET address=?,city=?,contactno=?,bank_acc_no=?,emailid=? where  empcode=?";
        try {
            pst=this.connection.prepareStatement(sql);
            pst.setString(1,acc.getAddress());
            pst.setString(2,acc.getCity());
            pst.setString(3,acc.getContactNo());
            pst.setString(4,acc.getBankAccount());
            pst.setString(5,acc.getEmail());
            pst.setString(6,empcode);
            res=pst.executeUpdate();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(res>0)
            return true;
        return false;
    }


  //SELECT a.extime - a.etime AS Difference FROM Present a
/*
  SELECT designation,department,hourlySalary,edate from EmployeeInfo
    join Present on Present.empid=EmployeeInfo.empcode WHERE empcode="104"
*/

}
