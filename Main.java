import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        
        while (true) {
            System.out.println("\n=== 学生管理系统 ===");
            System.out.println("1. 添加学生");
            System.out.println("2. 查询学生");
            System.out.println("3. 显示所有学生");
            System.out.println("4. 计算平均分");
            System.out.println("5. 退出");
            System.out.print("请选择操作 (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符
            
            switch (choice) {
                case 1:
                    System.out.print("请输入学生ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("请输入学生姓名: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("请输入学生性别: ");
                    String gender = scanner.nextLine();
                    
                    System.out.print("请输入班级: ");
                    String className = scanner.nextLine();
                    
                    System.out.print("请输入高数成绩: ");
                    double mathScore = scanner.nextDouble();
                    
                    System.out.print("请输入Java成绩: ");
                    double javaScore = scanner.nextDouble();
                    
                    Student student = new Student(id, name, gender, className, mathScore, javaScore);
                    manager.addStudent(student);
                    break;
                    
                case 2:
                    System.out.print("请输入要查询的学生ID: ");
                    int searchId = scanner.nextInt();
                    Student foundStudent = manager.getStudentById(searchId);
                    
                    if (foundStudent != null) {
                        System.out.println("找到学生:");
                        System.out.println("ID: " + foundStudent.getId());
                        System.out.println("姓名: " + foundStudent.getName());
                        System.out.println("性别: " + foundStudent.getGender());
                        System.out.println("班级: " + foundStudent.getClassName());
                        System.out.println("高数成绩: " + foundStudent.getMathScore());
                        System.out.println("Java成绩: " + foundStudent.getJavaScore());
                    } else {
                        System.out.println("未找到该学生！");
                    }
                    break;
                    
                case 3:
                    List<Student> students = manager.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("暂无学生信息！");
                    } else {
                        System.out.println("所有学生信息:");
                        for (Student s : students) {
                            System.out.println("ID: " + s.getId() + ", 姓名: " + s.getName() + 
                                             ", 性别: " + s.getGender() + ", 班级: " + s.getClassName() +
                                             ", 高数: " + s.getMathScore() + ", Java: " + s.getJavaScore());
                        }
                    }
                    break;
                    
                case 4:
                    System.out.println("计算平均分:");
                    manager.calculateAverageScores();
                    break;
                    
                case 5:
                    System.out.println("感谢使用学生管理系统，再见！");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }
}