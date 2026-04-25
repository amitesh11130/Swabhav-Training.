package com.amitesh.menu;

import java.util.Scanner;

import com.amitesh.model.Branch;
import com.amitesh.model.Course;
import com.amitesh.model.Registration;
import com.amitesh.model.Student;
import com.amitesh.service.StudentService;
import com.amitesh.service.StudentServiceInterface;
import com.amitesh.util.InputHandler;

public class StudentManagementMenu {

	Scanner scanner = new Scanner(System.in);
	StudentServiceInterface service = new StudentService();

	public void chooseOperation() {

		while (true) {

			System.out.println("\n======================================");
			System.out.println("     STUDENT MANAGEMENT SYSTEM");
			System.out.println("======================================");

			System.out.println("1. Add New Student");
			System.out.println("2. Register Student for Course");
			System.out.println("3. View All Students with Courses");
			System.out.println("4. Search Student by ID");
			System.out.println("5. Update Student Details");
			System.out.println("6. Update Course Fee");
			System.out.println("7. Cancel Course Registration");
			System.out.println("8. Delete Student");
			System.out.println("9. Show High Paying Students");
			System.out.println("10. Show Course-wise Student Count");
			System.out.println("11. Add New Course");
			System.out.println("12. Add New Branch");
			System.out.println("13. Exit");

			System.out.println("======================================");

			int choice = InputHandler.getValidIntInput(scanner, "Enter your choice (1-11): ");

			switch (choice) {

			case 1:
				System.out.println("\n Add New Student");

				int id;

				while (true) {
					id = InputHandler.getValidIntInput(scanner, "Enter Student ID: ");

					if (service.isStudentExists(id)) {
						System.out.println("Student ID already exists! Please enter a different ID.");
					} else {
						break;
					}
				}

				String name = InputHandler.getValidStringInput(scanner, "Enter Name: ");
				int age = InputHandler.getValidIntInput(scanner, "Enter Age: ");

				int branchId;
				service.showBranches();

				while (true) {
					branchId = InputHandler.getValidIntInput(scanner, "Enter Branch ID: ");

					if (service.isValidBranch(branchId)) {
						break;
					} else {
						System.out.println("Invalid Branch ID! Please choose from the list above.");
					}
				}

				Student student = new Student(id, name, age, branchId);
				service.addStudent(student);
				break;

			case 2:
				System.out.println("\n Register Student for Course");

				int sid;

				while (true) {
					sid = InputHandler.getValidIntInput(scanner, "Enter Student ID: ");

					if (service.isStudentExists(sid)) {
						break;
					} else {
						System.out.println("Student not found! Enter valid ID.");
					}
				}
				int cid;
				service.showCourses();
				while (true) {
					cid = InputHandler.getValidIntInput(scanner, "Enter Course ID: ");

					if (service.isValidCourse(cid)) {
						break;
					} else {
						System.out.println("Invalid Course ID! Please choose from the list above.");
					}
				}
				double fee = InputHandler.getValidDoubleInput(scanner, "Enter Fee Paid: ");

				Registration registration = new Registration(sid, cid, fee);
				service.registerCourse(registration);
				break;

			case 3:
				System.out.println("\n Student List");
				service.viewAllStudents();
				break;

			case 4:
				System.out.println("\n Search Student");

				service.getStudent(InputHandler.getValidIntInput(scanner, "Enter Student ID: "));
				break;

			case 5:
				System.out.println("\n Update Student Details");
				int UpdateId;

				while (true) {
					UpdateId = InputHandler.getValidIntInput(scanner, "Enter Student ID To Update: ");

					if (service.isStudentExists(UpdateId)) {
						break;
					} else {
						System.out.println("Student not found! Enter valid ID.");
					}
				}

				String updateName = InputHandler.getValidStringInput(scanner, "Enter New Name: ");
				int updateBranchId;
				service.showBranches();

				while (true) {
					updateBranchId = InputHandler.getValidIntInput(scanner, "Enter New Branch ID: ");

					if (service.isValidBranch(updateBranchId)) {
						break;
					} else {
						System.out.println("Invalid Branch ID! Please choose from the list above.");
					}
				}

				service.updateStudent(updateName, updateBranchId, UpdateId);
				break;

			case 6:
				System.out.println("\n Update Course Fee");

				int studentId = InputHandler.getValidIntInput(scanner, "Enter Student ID: ");
				int courseId = InputHandler.getValidIntInput(scanner, "Enter Course ID: ");
				double newFee = InputHandler.getValidDoubleInput(scanner, "Enter New Fee: ");

				service.updateFee(studentId, courseId, newFee);
				break;

			case 7:
				System.out.println("\n Cancel Course Registration");

				service.cancelRegistration(InputHandler.getValidIntInput(scanner, "Enter Student ID: "));
				break;

			case 8:
				System.out.println("\n Delete Student");

				service.deleteStudent(InputHandler.getValidIntInput(scanner, "Enter Student ID: "));
				break;

			case 9:
				System.out.println("\n High Paying Students");

				service.showHighPaying(InputHandler.getValidDoubleInput(scanner, "Enter Minimum Total Fee: "));
				break;

			case 10:
				System.out.println("\n Course Wise Student Count");

				service.showCourseWiseCount();
				break;

			case 11:
				System.out.println("\n Add New Course");

				String courseName = InputHandler.getValidStringInput(scanner, "Enter Course Name: ");
				double feeAmount = InputHandler.getValidDoubleInput(scanner, "Enter Course Fee: ");

				Course course = new Course(courseName, feeAmount);
				service.addCourse(course);

				break;

			case 12:
				System.out.println("\n Add New Branch");

				String branchName = InputHandler.getValidStringInput(scanner, "Enter Branch Name: ");

				Branch branch = new Branch(branchName);
				service.addBranch(branch);

				break;

			case 13:
				System.out.println("\n======================================");
				System.out.println("Thank you for using the system!");
				System.out.println("Exiting application...");
				System.out.println("======================================");

				scanner.close();
				return;

			default:
				System.out.println("Invalid choice! Please try again.");
			}
		}
	}
}