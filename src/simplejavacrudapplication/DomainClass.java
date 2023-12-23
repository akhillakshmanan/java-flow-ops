package simplejavacrudapplication;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Contains the different methods to store, manipulate and retrive data.
 *
 */
public class DomainClass {
	private static final Map<String, JsonObject> department1 = new HashMap<>();
	private static final Map<Integer, JsonObject> students = new HashMap<>();
	private static final Map<Integer, JsonObject> teachers = new HashMap<>();

	public static Map<Integer, JsonObject> getStudents() {
		return students;
	}

	public static Map<Integer, JsonObject> getTeachers() {
		return teachers;
	}

	public static Map<String, JsonObject> getDepartments() {
		return department1;
	}

	/**
	 * @param jsoninput
	 * @param choice
	 * @param manipulation process input for different cases
	 */
	static void processInput(String jsoninput, String choice, String manipulation) {
		if (manipulation == "c") {
			if (choice == "Department") {
				JsonObject record=(JsonObject) JsonParser.parseString(jsoninput);
				processDepartment(record);
			} else if (choice == "Teacher") {
				JsonObject record=(JsonObject) JsonParser.parseString(jsoninput);
				processTeacher(record);
			} else if (choice == "Student") {
				JsonObject record=(JsonObject) JsonParser.parseString(jsoninput);
				processStudent(record);
			}
		} else if (manipulation == "u") {
			if (choice == "Department") {
				JsonObject record=(JsonObject) JsonParser.parseString(jsoninput);				
				updateDepartment(record);
			} else if (choice == "Teacher") {
				JsonObject record=(JsonObject) JsonParser.parseString(jsoninput);				
				updateTeacher(record);
			} else if (choice == "Student") {
				JsonObject record=(JsonObject) JsonParser.parseString(jsoninput);				
				updateStudent(record);
			}

		} else if (manipulation == "d") {
			if (choice == "Department") {
				String record=jsoninput;
				deleteDepartment(record);
			} else if (choice == "Teacher") {
				JsonObject record=(JsonObject) JsonParser.parseString(jsoninput);
				deleteTeacher(record);
			} else if (choice == "Student") {
				JsonObject record=(JsonObject) JsonParser.parseString(jsoninput);
				deleteStudent(record);
			}
		}
	}

	private static void deleteStudent(JsonObject student) {
		try {
			JsonObject studentd = students.get(student.get("rollNo").getAsInt());
			if (studentd == null) {
				System.out.println("Invalid Student roll no to delete: " + student.get("rollNo").getAsInt());
				return;
			}
			JsonObject department = department1.get(student.get("department").getAsString());
			if (department.get("noOfStudents").getAsInt() > 0) {
				students.remove(student.get("rollNo").getAsInt());
				department.addProperty("noOfStudents", department.get("noOfStudents").getAsInt()-1);
				System.out.println("Student Details deleted: " + student);
			} else
				System.out.println("No proper department details are present please update department details");
		} catch (Exception e) {
			System.out.println("Unknown exception occured please try with valid details");
		}

	}

	private static void deleteTeacher(JsonObject teacher) {
		try {
			JsonObject teacherd = teachers.get(teacher.get("staffId").getAsInt());
			if (teacherd == null) {
				System.out.println("Invalid Staffid to delete: " + teacher.get("staffId").getAsString());
				return;
			}
			JsonObject department = department1.get(teacher.get("department").getAsString());
			if (department.get("noOfTeachers").getAsInt() > 0) {
				teachers.remove(teacher.get("staffId").getAsInt());
				department.addProperty("noOfTeachers", department.get("noOfTeachers").getAsInt()-1);
				System.out.println("Teacher Details deleted: " + teacher);
			} else
				System.out.println("No proper department details are present please update department details");
		} catch (Exception e) {
			System.out.println("Unknown exception occured please try with valid details");
		}

	}

	private static void deleteDepartment(String departmen) {
		try {
			JsonObject departmentDetCheck = department1.get(departmen);
			if (departmentDetCheck.get("noOfStudents").getAsInt() <= 0 && departmentDetCheck.get("noOfTeachers").getAsInt() <= 0
					&& departmentDetCheck != null) {
				department1.remove(departmen);
			} else
				System.out.println(
						"Can't delete department as valid student and teachers details are present please delete those and come back!");
		} catch (Exception e) {
			System.out.println("No such deparment is present just enter the department name only");
		}

	}

	private static void updateStudent(JsonObject student) {
		JsonObject rollno = students.get(student.get("rollNo").getAsInt());
		if (rollno == null) {
			System.out.println("Invalid roll no: " + rollno);
			return;
		}
		students.replace(student.get("rollNo").getAsInt(), rollno, student);
		System.out.println("Student Details updated: " + student);

	}

	private static void updateTeacher(JsonObject teacher) {
		JsonObject staffid = teachers.get(teacher.get("staffId").getAsInt());
		if (staffid == null) {
			System.out.println("Invalid StaffId: " + staffid);
			return;
		}
		teachers.replace(teacher.get("staffId").getAsInt(), staffid, teacher);
		System.out.println("Teacher Details updated: " + teacher);

	}

	private static void updateDepartment(JsonObject department) {
		JsonObject departmentu = department1.get(department.get("name").getAsString());
		if (departmentu == null) {
			System.out.println("Invalid department name: " + departmentu);
			return;
		}
		department1.replace(department.get("name").getAsString(), departmentu, department);
		System.out.println("Department updated: " + department);
	}

	private static void processTeacher(JsonObject teacher) {
		if (teacher.get("staffId").getAsInt() != 0 && !teachers.containsKey(teacher.get("staffId").getAsInt())) {
			JsonObject department = department1.get(teacher.get("department").getAsString());
			if (department == null) {
				System.out.println("Invalid department: " + teacher.get("department").getAsString());
				return;
			}
			teachers.put(teacher.get("staffId").getAsInt(), teacher);
			System.out.println("Teacher added: " + teacher);
			department.addProperty("noOfTeachers", department.get("noOfTeachers").getAsInt()+1);
		} else
			System.out.println("Staffid can't be empty or staffid is already present or invalid department name");
	}

	private static void processStudent(JsonObject student) {
		if (student.get("rollNo").getAsInt() != 0 && !students.containsKey(student.get("rollNo").getAsInt())) {
			JsonObject department = department1.get(student.get("department").getAsString());
			if (department == null) {
				System.out.println("Invalid department: " + student.get("department").getAsString());
				return;
			}
			students.put(student.get("rollNo").getAsInt(), student);
			System.out.println("Student added: " + student);
			department.addProperty("noOfStudents", department.get("noOfStudents").getAsInt()+1);
		} else
			System.out.println("Roll no can't be null or roll no is already present or invalid department name");
	}

	private static void processDepartment(JsonObject department) {
	System.out.println(department.get("name").getAsString());
	if (department.get("name").getAsString() != null && !department1.containsKey(department.get("name").getAsString())) {
			department1.put(department.get("name").getAsString(), department);
			System.out.println("Department added: " + department);
		} else
			System.out.println("Department name can't be null or department is already present");
	}

	/**
	 * @param choice For displaying data of particular data.
	 */
	public static void displaychoices(String choice) {
		if (choice == "Department") {
			department1.forEach((k, v) -> System.out.println("\nValue = " + v));

		} else if (choice == "Teacher") {
			teachers.forEach((k, v) -> System.out.println("\nValue = " + v));
		} else if (choice == "Student") {
			students.forEach((k, v) -> System.out.println("\nValue = " + v));
		}

	}

	public static void showDepartmentWiseStudentandTeachersList(String departmentkey) {
		JsonObject departmentDetCheck = department1.get(departmentkey);
		if (departmentDetCheck != null && departmentkey != null) {
			System.out.printf("List of teachers in %s:\n", departmentkey);
			teachers.values().stream().filter(t -> t.get("department").getAsString().contains(departmentkey.toString()))
					.forEach(t -> System.out.println(t));
			System.out.printf("List of students in %s:\n", departmentkey);
			List<JsonObject>result=students.values().stream().filter(t -> t.get("department").getAsString().contains(departmentkey))
					.collect(Collectors.toList());
			System.out.println(result);
			

		} else
			System.out.println("Either Department key is invalid or department is not present in the database.");

	}

//	private static void deleteDepartment(String departmen) {
//		Department departmentDetCheck = departments.get(departmen);
//		if (departmentDetCheck.getNoOfStudents() <= 0 && departmentDetCheck.getNoOfTeachers() <= 0) {
//			departments.remove(departmen);
//		} else
//			System.out.println(
//					"Can't delete department as valid student and teachers details are present please delete those and come back!");
//
//	}

}