package beans;

public class Appartement{
	
	private String description;
	private int loc_etage;
	private boolean ascenseur;
	private boolean cuisine_equipe;
	private String num_bi;	
	
	private int code_classe;
	private String num_prop; 
	private float prix_achat;
	private float loyer_mensuel;
	private int nb_chambre;

	public Appartement(String num_bi, int loc_etage, boolean ascenseur, boolean cuisine_equipe,
			int code_classe, String num_prop, float prix_achat, int nb_chambre) {
		super();
		this.num_bi=num_bi;
		this.loc_etage = loc_etage;
		this.ascenseur = ascenseur;
		this.cuisine_equipe = cuisine_equipe;
		this.code_classe = code_classe;
		this.num_prop = num_prop;
		this.prix_achat = prix_achat;
		this.nb_chambre = nb_chambre;
	}


	public Appartement(String num_bi, int loc_etage, boolean ascenseur, boolean cuisine_equipe, String num_bi2,
			int code_classe, String num_prop,int nb_chambre, float loyer_mensuel) {
		super();
		this.num_bi=num_bi;
		this.loc_etage = loc_etage;
		this.ascenseur = ascenseur;
		this.cuisine_equipe = cuisine_equipe;
		this.code_classe = code_classe;
		this.num_prop = num_prop;
		this.loyer_mensuel = loyer_mensuel;
		this.nb_chambre = nb_chambre;
	}


	public Appartement(String num_bi) {
		super();
		this.num_bi = num_bi;
	}

	public Appartement(String num_bi,String Description,int Loc_etage, boolean Ascenseur, boolean Cuisine_equipe) {
		super();
		this.num_bi=num_bi;
		this.description = Description;
		this.loc_etage = Loc_etage;
		this.ascenseur = Ascenseur;
		this.cuisine_equipe = Cuisine_equipe;
	}

	public Appartement() {
		super();
	}
	
	public String getNum_bi() {
		return num_bi;
	}

	public void setNum_bi(String num_bi) {
		this.num_bi = num_bi;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLoc_etage() {
		return loc_etage;
	}

	public void setLoc_etage(int loc_etage) {
		this.loc_etage = loc_etage;
	}

	public boolean getAscenseur() {
		return ascenseur;
	}

	public void setAscenseur(boolean ascenseur) {
		this.ascenseur = ascenseur;
	}

	public boolean getCuisine_equipe() {
		return cuisine_equipe;
	}

	public void setCuisine_equipe(boolean cuisine_equipe) {
		this.cuisine_equipe = cuisine_equipe;
	}

	public int getCode_classe() {
		return code_classe;
	}

	public void setCode_classe(int code_classe) {
		this.code_classe = code_classe;
	}

	public String getNum_prop() {
		return num_prop;
	}

	public void setNum_prop(String num_prop) {
		this.num_prop = num_prop;
	}

	public float getPrix_achat() {
		return prix_achat;
	}

	public void setPrix_achat(float prix_achat) {
		this.prix_achat = prix_achat;
	}

	public float getLoyer_mensuel() {
		return loyer_mensuel;
	}

	public void setLoyer_mensuel(float loyer_mensuel) {
		this.loyer_mensuel = loyer_mensuel;
	}

	public int getNb_chambre() {
		return nb_chambre;
	}

	public void setNb_chambre(int nb_chambre) {
		this.nb_chambre = nb_chambre;
	}
}