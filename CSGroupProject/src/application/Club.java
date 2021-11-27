package application;

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
		return name + " " + link + " " + email + " " + category + " " + school + "\n";
	}
	
	
}
