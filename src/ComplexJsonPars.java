
import files.payload;
import io.restassured.path.json.JsonPath;
public class ComplexJsonPars {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js=new JsonPath(payload.CoursePrice());
		
		// print no of courses returned by API
		int Count=	js.getInt("courses.size()");  // Size is method to get the count for array
		System.out.println("Count is :" +Count);
		
		//Print Purchase Amount
		int Amount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount :" +Amount);
		
		//Print Title of the first course
		String firstCourseTitle = js.getString("courses[0].title");
		System.out.println("First course title : " + firstCourseTitle);
		
		//Print All course titles and their respective Prices
		for(int i=0; i<Count; i++)
		{
			String courseTitles=js.get("courses["+ i +"].title");
			System.out.println(js.get("courses["+ i +"].price").toString());	 
			System.out.println(courseTitles);
		}
		
		//Print no. of copies sold by RPA Course
		for(int i=0; i<Count; i++)
		{
			String courseTitle = js.getString("courses[" + i + "].title");
			if(courseTitle.equalsIgnoreCase("RPA"))
			{
				System.out.println("Number of copies for RPA course is " + js.getInt("courses[" + i + "].copies"));
				break;
			}
		}
	}
}