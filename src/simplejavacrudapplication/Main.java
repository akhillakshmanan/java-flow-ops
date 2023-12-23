package simplejavacrudapplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to the college database application!");
		firstpage();
	}

	/**
	 * Method to load first page of the application
	 */
	private static void firstpage() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {

			System.out.println(
					"**************************************************************************************************************************");
			System.out.println("College Details");
			System.out.println("""
					 For Department:1
					 For Teacher:2
					 For Student:3
					 Display All:4
					 
					 press 5 to exit
				               """);
			

			String input1 = scanner.next().trim();
			while (isValidInput(input1)) {
				int choice = Integer.parseInt(input1);
				if (choice == 5)
					System.out.println("***********Exiting Application**************");
				else
					System.out.println("Enter your choice");
				switch (choice) {
				case 1:
					secondpage("Department");
					break;
				case 2:
					secondpage("Teacher");
					break;
				case 3:
					secondpage("Student");
					break;
				case 4:
					displayAll();
					break;
//					try {
//						saveAll();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					break;
				case 5:
					System.exit(1);
					break;
				case 7:
					try {
						saveAll();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				break;
			}

		}

	}

	/**
	 * @param choice Method receives the different choices and takes particular json
	 *               data as input
	 */
	public static void secondpage(String choice) {
		System.out.printf(
				"To Handle %s data:\nFor create->c\nFor Delete->d\nFor Update->u\nFor View all data in %s database->v\n For view list of teachers and students in particular department->p"
						+ "\n\nTo go back press b.",
				choice, choice);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String crudchoice = scan.nextLine();
		while (true) {
			try {
				System.out.println(
						"\n**************************************************************************************************************************\n");
				switch (crudchoice) {
				case "c":
					System.out.printf("Enter %s Details:\n", choice);
					String jsonc = scan.nextLine();
					DomainClass.processInput(jsonc, choice, "c");
					break;
				case "d":
					System.out.printf("Delete %s Details:\n", choice);
					String deletekey = scan.nextLine();
					DomainClass.processInput(deletekey, choice, "d");
					break;
				case "u":
					System.out.printf("Update %s Details:\n", choice);
					String jsonu = scan.nextLine();
					DomainClass.processInput(jsonu, choice, "u");
					break;
				case "v":
					System.out.printf("Viewing %s Details:\n", choice);
					DomainClass.displaychoices(choice);
				case "b":
					firstpage();
					break;
				case "p":
					System.out.println("Enter Department name to view department wise details");
					String departmentkey = scan.nextLine();
					DomainClass.showDepartmentWiseStudentandTeachersList(departmentkey);
					break;
				default:
					System.out.println("The input value is invalid");
					secondpage(choice);
					break;
				}
				break;
			} catch (Exception e) {
				System.out.println("Uh oh...Enter proper json");
			}
		}

	}

	/**
	 * Display's all data stored in the database.
	 */
	public static void displayAll() {
		System.out.println("List of Departments:\n");
		DomainClass.getDepartments().forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
		System.out.println(
				"\n**************************************************************************************************************************\n");
		System.out.println("List of Teachers:\n");
		DomainClass.getTeachers().forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
		System.out.println(
				"\n**************************************************************************************************************************\n");
		System.out.println("List of Students\n");
		DomainClass.getStudents().forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));

	}

	/**
	 * @param input1 checks the input data is a valid data.
	 * @return true true or false according to validity of data
	 */
	public static boolean isValidInput(String input1) {
		try {
			int parint = Integer.parseInt(input1);
			if (parint > 0 && parint <= 5)
				return true;
			else
				System.out.println("Input value must be between 1 to 5");
			return false;
		} catch (NumberFormatException e) {
			System.out.println("The input cannot be a character please input value between 1 to 5");
			return false;
		}
	}
	private static void saveAll() throws IOException {
		Path pathname=Paths.get("./resources/data.txt");
		String departmentdata=DomainClass.getDepartments().toString();
		String teacherdata=DomainClass.getTeachers().toString();
		String studentdata=DomainClass.getStudents().toString();
		ArrayList<String> list=new ArrayList<>();
		list.add(departmentdata);
		list.add(teacherdata);
		list.add(studentdata);
		Files.write(pathname, list);
	}

}
