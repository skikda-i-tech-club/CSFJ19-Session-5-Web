package utils;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import beans.Appartement;
import ressource.UserAccount;
 
public class DBUtils {
	String cla;
	/////////user finder////////////////////////////////////////////
	
	public static void insertUser(Connection conn, UserAccount user) throws SQLException {
    	String sql=null;
    	PreparedStatement pstm =null;
        sql = "INSERT INTO compte(email,nom,prenom,pass,nature) VALUES (?,?,?,?,?)";
 
        pstm = conn.prepareStatement(sql);
 
        pstm.setString(2, user.getNom());
        pstm.setString(3, user.getPrenom());
        pstm.setString(4, user.getPassword());
        pstm.setString(5, "Client");
        pstm.setString(1, user.getUserName());
        pstm.executeUpdate();
        
    }
	
	public static UserAccount findCompteType(Connection conn, String username, String pass) throws SQLException {
		 
	    String sql = "SELECT a.NATURE FROM COMPTE a WHERE a.EMAIL = ? AND a.PASS = ? ";
	    PreparedStatement pstm = conn.prepareStatement(sql);
	    pstm.setString(1, username);
	    pstm.setString(2, pass);
	    ResultSet rs = pstm.executeQuery();
	    if (rs.next()) {
	        String nature = rs.getString("NATURE");
	        UserAccount user = new UserAccount();
	        user.setNature(nature);
	        return user;
	    }
	    return null;
	}
	public static UserAccount findUser(Connection conn, String username, String password) throws SQLException {
 	    
        String sql = "SELECT a.NOM, a.PRENOM from Compte a " //
                + " where a.EMAIL = ? and a.pass= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            
        	String nom = rs.getString("nom");
        	String prenom = rs.getString("prenom");
        	
        	UserAccount user = new UserAccount();
            user.setUserName(username); 
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setPassword(password);
            return user;
        }
        return null;
    }
	
	public static UserAccount findUser(Connection conn, String userName) throws SQLException {
		 
        String sql = "SELECT a.EMAIL, a.PASS\r\n" + 
        		"        FROM COMPTE a \r\n" + 
        		"        WHERE\r\n" + 
        		"            a.EMAIL = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("Password");
  
            UserAccount user = new UserAccount();
            user.setNom(userName);
            user.setPassword(password);
            
            return user;
        }
        return null;
    }
	
	//////////////////////APPARTEMENT CRUD //////////////////////////////////////////
	
	public static Appartement ConsulterAppartement(Connection conn, String num_bi) throws SQLException {
		String sql=null;
		PreparedStatement pstm =null;
		ResultSet rs =null;
		sql="SELECT a.Num_Bi, a.Description, a.Loc_Etage, a.Ascenseur, a.Cuisine_Equipe FROM appartement a \r\n" + 
				" WHERE a.Num_Bi=?";
		
		pstm = conn.prepareStatement(sql);
        pstm.setString(1, num_bi);
        rs = pstm.executeQuery();
        
        if (rs.next()) {
        	String Descripion=rs.getString("Description");
            int Loc_Etage=rs.getInt("Loc_Etage");
            boolean Ascenseur=rs.getBoolean("Ascenseur");
            boolean Cuisine_Equipe=rs.getBoolean("Cuisine_Equipe");      
        	Appartement appartement = new Appartement();
        	appartement.setNum_bi(num_bi);
        	appartement.setDescription(Descripion);
        	appartement.setLoc_etage(Loc_Etage);
        	appartement.setAscenseur(Ascenseur);
        	appartement.setCuisine_equipe(Cuisine_Equipe);		
		return appartement;
    }
    return null;
	}
  
    public static List<Appartement> ConsulterListeAppartement(Connection conn) throws SQLException {
				String sql=null;
				PreparedStatement pstm =null;
				ResultSet rs =null;
    	sql = "SELECT a.Num_Bi,a.Loc_Etage,a.Ascenseur,a.Cuisine_Equipe,a.nb_chambre,\r\n" + 
    			"b.Prix_Achat,b.Num_Prop,b.Code_Classe\r\n" + 
    			"FROM appartement a\r\n" + 
    			"join bien_immobilier b\r\n" + 
    			"on a.Num_Bi=b.Num_Bi";
 
        pstm = conn.prepareStatement(sql);
        
        rs = pstm.executeQuery();
        List<Appartement> list = new ArrayList<Appartement>();
 
        while (rs.next()) {
        	String Num_Bi=rs.getString("Num_Bi");
        	int loc_etage=rs.getInt("Loc_Etage");
        	boolean ascenseur=rs.getBoolean("Ascenseur");
        	boolean cuisine_equipe=rs.getBoolean("Cuisine_Equipe");
        	int nb_chambre=rs.getInt("nb_chambre");
        	float prix_achat=rs.getFloat("Prix_Achat");
        	String num_prop=rs.getString("Num_Prop");
            int code_classe=rs.getInt("Code_Classe");
            
            System.out.println(Num_Bi);
            		
           Appartement appartement = new Appartement();
           appartement.setNum_bi(Num_Bi);
           appartement.setLoc_etage(loc_etage);
           appartement.setAscenseur(ascenseur);
           appartement.setCuisine_equipe(cuisine_equipe);
           appartement.setNb_chambre(nb_chambre);
           appartement.setPrix_achat(prix_achat);
           appartement.setNum_prop(num_prop);
           appartement.setCode_classe(code_classe);

            list.add(appartement);
        }
        return list;
    }
    
    public static List<Appartement> ConsulterListeAppartementM(Connection conn,String prest,String obj,
    		int Loc_Etage, boolean Ascenseur,boolean Cuisine_Equipe,int nb_chambre,
    		float Prix_Achat) throws SQLException {
    	String sql = "SELECT a.Num_Bi,a.Loc_Etage,a.Ascenseur,a.Cuisine_Equipe,a.nb_chambre,\r\n" + 
    			"b.Prix_Achat,b.Num_Prop,b.Code_Classe\r\n" + 
    			"FROM appartement a\r\n" + 
    			"join bien_immobilier b\r\n" + 
    			"on a.Num_Bi=b.Num_Bi \r\n"+
    			"where b.objet =  ?\r\n" + 
    			"AND b.type = ? and a.Loc_Etage=? and a.Ascenseur=? and \r\n"+
    			"a.Cuisine_Equipe=? and a.nb_chambre=? and b.Prix_Achat<?";	
 
    	PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, prest);
        pstm.setString(2, obj);
        pstm.setInt(3, Loc_Etage);
        pstm.setBoolean(4, Ascenseur);
        pstm.setBoolean(5, Cuisine_Equipe);
        pstm.setInt(6, nb_chambre);
        pstm.setFloat(7, Prix_Achat);
        ResultSet rs = pstm.executeQuery();
        List<Appartement> list = new ArrayList<Appartement>();
 
        while (rs.next()) {
        	String Num_Bi=rs.getString("Num_Bi");
        	Loc_Etage=rs.getInt("Loc_Etage");
        	Ascenseur=rs.getBoolean("Ascenseur");
        	Cuisine_Equipe=rs.getBoolean("Cuisine_Equipe");
        	nb_chambre=rs.getInt("nb_chambre");
        	Prix_Achat=rs.getFloat("Prix_Achat");
        	String num_prop=rs.getString("Num_Prop");
            int code_classe=rs.getInt("Code_Classe");
            Appartement appartement = new Appartement();
            appartement.setNum_bi(Num_Bi);
            appartement.setLoc_etage(Loc_Etage);
            appartement.setAscenseur(Ascenseur);
            appartement.setCuisine_equipe(Cuisine_Equipe);
            appartement.setNb_chambre(nb_chambre);
            appartement.setPrix_achat(Prix_Achat);
            appartement.setNum_prop(num_prop);
            appartement.setCode_classe(code_classe);

            list.add(appartement);
        }
        return list;
    }
    
    public static List<Appartement> ConsulterListeAppartementL(Connection conn,String prest,String obj) throws SQLException {
    	String sql = "SELECT a.Num_Bi,a.Loc_Etage,a.Ascenseur,a.Cuisine_Equipe,a.nb_chambre,\r\n" + 
    			"b.Prix_Achat,b.Num_Prop,b.Code_Classe\r\n" + 
    			"FROM appartement a\r\n" + 
    			"join bien_immobilier b\r\n" + 
    			"on a.Num_Bi=b.Num_Bi \r\n"+
    			"where b.objet =  ?\r\n" + 
    			"AND b.type =  ?";	
 
    	PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, prest);
        pstm.setString(2, obj);
        ResultSet rs = pstm.executeQuery();
        List<Appartement> list = new ArrayList<Appartement>();
 
        while (rs.next()) {
        	String Num_Bi=rs.getString("Num_Bi");
        	int loc_etage=rs.getInt("Loc_Etage");
        	boolean ascenseur=rs.getBoolean("Ascenseur");
        	boolean cuisine_equipe=rs.getBoolean("Cuisine_Equipe");
        	int nb_chambre=rs.getInt("nb_chambre");
        	float prix_achat=rs.getFloat("Prix_Achat");
        	String num_prop=rs.getString("Num_Prop");
            int code_classe=rs.getInt("Code_Classe");
             
            Appartement appartement = new Appartement();
            appartement.setNum_bi(Num_Bi);
            appartement.setLoc_etage(loc_etage);
            appartement.setAscenseur(ascenseur);
            appartement.setCuisine_equipe(cuisine_equipe);
            appartement.setNb_chambre(nb_chambre);
            appartement.setPrix_achat(prix_achat);
            appartement.setNum_prop(num_prop);
            appartement.setCode_classe(code_classe);

            list.add(appartement);
        }
        return list;
    }
    
    public static void updateAppartement(Connection conn, Appartement appartement) throws SQLException {
    	String sql=null;
    	PreparedStatement pstm =null;
        sql = "UPDATE appartement SET Description=?,Loc_Etage=?,\r\n"
        + "Ascenseur=?,Cuisine_Equipe=? WHERE Num_Bi=?";
 
        pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, appartement.getDescription());
        pstm.setInt(2, appartement.getLoc_etage());
        pstm.setBoolean(3, appartement.getAscenseur());
        pstm.setBoolean(4, appartement.getCuisine_equipe());
        pstm.setString(5, appartement.getNum_bi());
        pstm.executeUpdate();
    }
       
    public static void deleteAppartement(Connection conn, String Num_Bi) throws SQLException {
        String sql = "delete from appartement WHERE Num_Bi=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, Num_Bi);
        pstm.executeUpdate();
    }
    
    public static void insertAppartement(Connection conn, Appartement appartement) throws SQLException {
    	String sql=null;
    	PreparedStatement pstm =null;
        sql = "INSERT INTO appartement(Num_Bi, Description, Loc_Etage, Ascenseur,\r\n "
        		+ "Cuisine_Equipe) VALUES (?,?,?,?,?)";
 
        pstm = conn.prepareStatement(sql);
 
        pstm.setString(2, appartement.getDescription());
        pstm.setInt(3, appartement.getLoc_etage());
        pstm.setBoolean(4, appartement.getAscenseur());
        pstm.setBoolean(5, appartement.getCuisine_equipe());
        pstm.setString(1, appartement.getNum_bi());
        pstm.executeUpdate();
        
    }    
   }