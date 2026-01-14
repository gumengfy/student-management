import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (id, name, gender, class, math_score, java_score) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getClassName());
            pstmt.setDouble(5, student.getMathScore());
            pstmt.setDouble(6, student.getJavaScore());
            
            pstmt.executeUpdate();
            System.out.println("学生信息添加成功！");
            
        } catch (SQLException e) {
            System.out.println("添加学生信息失败！");
            e.printStackTrace();
        }
    }
    
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
            }
            
        } catch (SQLException e) {
            System.out.println("查询学生信息失败！");
            e.printStackTrace();
        }
        
        return student;
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
                students.add(student);
            }
            
        } catch (SQLException e) {
            System.out.println("获取所有学生信息失败！");
            e.printStackTrace();
        }
        
        return students;
    }
    
    public void calculateAverageScores() {
        String sql = "SELECT AVG(math_score) as avg_math, AVG(java_score) as avg_java FROM students";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                double avgMath = rs.getDouble("avg_math");
                double avgJava = rs.getDouble("avg_java");
                System.out.println("高数平均分: " + avgMath);
                System.out.println("Java平均分: " + avgJava);
            }
            
        } catch (SQLException e) {
            System.out.println("计算平均分失败！");
            e.printStackTrace();
        }
    }
}