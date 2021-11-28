package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Club {
	private String name;
	private String link;
	private String email;
	private String category;
	private String school;

	public Club(String name, String link, String email, String category, String school) {
		super();
		this.name = name;
		this.link = link;
		this.email = email;
		this.category = category;
		this.school = school;
	}
		//takes in a string to decide which data to add to an arrayList, then returns the arrayList
		public ArrayList<Club> loadData(String type) {
			ArrayList<Club> clubs = new ArrayList<>();
			String tempName = "";
			String tempLink = "";
			String tempEmail = "";
			String tempCategory = "";
			String tempSchool = "";
			String text = "";
			String[] items = new String[6];
			FileReader readFile;
			try {
				readFile = new FileReader("clubs.csv");
				BufferedReader buff = new BufferedReader(readFile);
				while((text = buff.readLine()) != null) {
					items = text.split(",");
					tempName = items[0];
					tempLink = items[1];
					tempEmail = items[2];
					tempSchool = items[3];
					tempCategory = items[4];
					Club tempClub = new Club(tempName, tempLink, tempEmail, tempCategory, tempSchool);
					if(type.equals("Display All Clubs")) {
						clubs.add(tempClub);
					}
					else if(type.equals("Science") || type.equals("Business") || type.equals("Engineering") || type.contains("Liberal and Fine Arts") || type.equals("Education")) {
							if(type.equals(tempClub.getSchool())) {
							clubs.add(tempClub);
							}
					}
					else if(type.contains(tempClub.getCategory())) {
							clubs.add(tempClub);
					}
					
				}
				
				buff.close();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			return clubs;
		}
		
		
		
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	@Override
	public String toString() {
		return name + " " + link + " " + email + " " + school + " " + category + "\n";
	}
	
	
}
