package application;

public class Profil {
public String nom,prenom,pseudo;

public Profil(String nom, String prenom, String pseudo) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.pseudo = pseudo;
}

@Override
public String toString() {
	return pseudo ;
}
	
}
