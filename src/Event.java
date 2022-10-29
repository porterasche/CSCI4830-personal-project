
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "USER")
	private String user;
	
	@Column(name = "START")
	private String start;
	
	@Column(name = "END")
	private String end;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "NAME")
	private String name;
	
	public Event() {}
	
	public Event(Integer id, String user, String start, String end, String date, String name) {
		this.id = id;
		this.user = user;
		this.start = start;
		this.end = end;
		this.date = date;
		this.name = name;
	}
	
	public Event(String user, String start, String end, String date, String name) {
		this.user = user;
		this.start = start;
		this.end = end;
		this.date = date;
		this.name = name;
	}

	public Integer getId() {
	      return id;
    }

    public void setId(Integer id) {
    	this.id = id;
    }
    
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
  	}
	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
  	}
	
	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
  	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
  	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
