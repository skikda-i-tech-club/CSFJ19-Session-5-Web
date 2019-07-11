package ressource;

public class UserAccount {
	    
	   private String nom;
	   private String prenom;
	   private String fonction;
	   private String activite;
	   private String password;
	   private String username;
	   private String nature;

	   public UserAccount() {
	   }
	    
	   public String getUserName() {
	       return username;
	   }
	 
	   public void setUserName(String username) {
	       this.username = username;
	   }
	 
	 
	   public String getPassword() {
	       return password;
	   }
	 
	   public void setPassword(String password) {
	       this.password = password;
	   }
	   
	   public String getNom() {
	       return nom;
	   }
	 
	   public void setNom(String nom) {
	       this.nom = nom;
	   }
	   
	   public String getPrenom() {
	       return prenom;
	   }
	 
	   public void setPrenom(String prenom) {
	       this.prenom = prenom;
	   }
	   
	   public String getFonction() {
	       return fonction;
	   }
	 
	   public void setFonction(String fonction) {
	       this.fonction = fonction;
	   }
	   
	   public String getActivite() {
	       return activite;
	   }
	 
	   public void setActivite(String activite) {
	       this.activite = activite;
	   }

	   public String getNature() {
		return nature;
	   }

	   public void setNature(String nature) {
		this.nature = nature;
	   }
	}