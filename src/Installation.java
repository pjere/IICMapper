import java.util.Vector;

import InputOutput.CSVReader;


public class Installation {


	private String S3ICNum; //1
	private String NomUsuel; //2
	private String RaisonSociale; //3
	private ICPERegime RegimeEtablissement; //5 et 6
	private String Commune; //7
	private String CodeINSEE; //8
	private String CodePostal; //9
	private String Adresse; //10 et 11 puis 9 puis 7
	private String Telephone; // 12
	private String Fax;//13
	private String Departement;// avec 8
	private String ServiceControle; //15
	private String AdresseAdministrative; //Adresse pour la facturation TGAP, etc.
	private String SIRET;
	private double Latitude;
	private double Longitude;
	private String NAFCode;
	private boolean SoumisRemiseEtat;
	private boolean EstPrioritaire;
	private boolean EstAEnjeux;
	private boolean CritereEau;
	private boolean CritereAir;
	private boolean CritereDechets;
	private boolean CritereSSP;
	private boolean CritereRisques;
	private boolean SoumisQuotas;
	private boolean SoumisGIC;
	private boolean SoumisIEDGIC;
	private boolean EstIncinerateur;
	private boolean SoumisReductionCOV;
	private boolean STEPIndustrielle;
	private boolean SoumisIPPC;
	private boolean IED;
	private boolean RejetsAqueux;
	private boolean RejetsAtmospheriques;
	private boolean DeclarationDechets;
	private boolean AutosurveillanceEauxSouterraines;
	private boolean SurveillanceMilieux;
	private boolean DeclarationEmissions;
	private boolean SoumisRSDE2002;
	private boolean SoumisRSDE2009;
	private boolean Amiante; //sites ayant utilisé de l'amiante.
	private boolean DepotPneumatiques;
	private boolean ImpactPlomb;
	private boolean ConsommateurCOV; //sites consommant plus de 30 tonnes de COV.
	private boolean SoumisGEREP;
	private boolean SoumisRSDE2004; //soumis réduction réelle substances dangereuses eau après 2004.
	private boolean SoumisREISTA;
	private boolean PlanModernisation; //AM du 4 oct 2010.
    private boolean OuvrageRetenue;
    private boolean OuvrageProtection;
    private boolean Epandages;
    private boolean SoumisTGAPAir;
    private boolean CHSCT;
    private boolean Milieu;
    private boolean PollutionAccidentelle;
    private boolean EMASISO14001;
    private String DateFinEMASISO14001;
    private boolean Secheresse;
    private boolean RemiseEtat;
    private boolean Carriere;
    private boolean GarantiesFinancieres;
    private boolean ResponsableDefaillant;
    private boolean Elevage;
    private boolean PreventionLegionellose;
    private String NumEDE;
    private String NumPACAGE;
    private String Vieillissement;
    private boolean SoumisPOI;
    private String DateMAJPOI;
    private boolean SoumisPAC;
    private String DatePAC;
    private boolean MaitriseUrbanismePIGSUP;
    private boolean NI140;
    
    public Installation(){}
    
    public Installation(String CSVLine){
    	Vector<String> VectorLine = CSVReader.ParseCSVLine(CSVLine);
    	
    	
    }
    
	public String getS3ICNum(){ return S3ICNum;}
	public String getNomUsuel(){ return NomUsuel;}
	public String getRaisonSociale(){ return RaisonSociale;}
	public ICPERegime getRegimeEtablissement(){return RegimeEtablissement;}
	public String getCommune(){ return Commune;}
	public String getCodeINSEE(){ return CodeINSEE;}
	public String getCodePostal(){ return CodePostal;}
	public String getAdresse(){ return Adresse;}
	public String getTelephone(){ return Telephone;}
	public String getFax(){ return Fax;}
	public String getDepartement(){ return Departement;}
	public String getServiceControle(){ return ServiceControle;}
	public String getAdresseAdministrative(){ return AdresseAdministrative;}
	public String getSIRET(){ return SIRET;}
	public double getLatitude(){ return Latitude;}
	public double getLongitude(){return Longitude;}
	public String getNAFCode(){ return NAFCode;}
	public boolean getSoumisRemiseEtat(){ return SoumisRemiseEtat;}
	public boolean getEstPrioritaire(){ return EstPrioritaire;}
	public boolean getEstAEnjeux(){ return EstAEnjeux;}
	public boolean getCritereEau(){ return CritereEau;}
	public boolean getCritereAir(){ return CritereAir;}
	public boolean getCritereDechets(){ return CritereDechets;}
	public boolean getCritereSSP(){ return CritereSSP;}
	public boolean getCritereRisques(){ return CritereRisques;}
	public boolean getSoumisQuotas(){ return SoumisQuotas;}
	public boolean getSoumisGIC(){ return SoumisGIC;}
	public boolean getSoumisIEDGIC(){ return SoumisIEDGIC;}
	public boolean getEstIncinerateur(){ return EstIncinerateur;}
	public boolean getSoumisReductionCOV(){ return SoumisReductionCOV;}
	public boolean getSTEPIndustrielle(){ return STEPIndustrielle;}
	public boolean getSoumisIPPC(){ return SoumisIPPC;}
	public boolean getIED(){ return IED;}
	public boolean getRejetsAqueux(){ return RejetsAqueux;}
	public boolean getRejetsAtmospheriques(){ return RejetsAtmospheriques;}
	public boolean getDeclarationDechets(){ return DeclarationDechets;}
	public boolean getAutosurveillanceEauxSouterraines(){ return AutosurveillanceEauxSouterraines;}
	public boolean getSurveillanceMilieux(){ return SurveillanceMilieux;}
	public boolean getDeclarationEmissions(){ return DeclarationEmissions;}
	public boolean getSoumisRSDE2002(){ return SoumisRSDE2002;}
	public boolean getSoumisRSDE2009(){ return SoumisRSDE2009;}
	public boolean getAmiante(){ return Amiante;} //sites ayant utilisé de l'amiante.
	public boolean getDepotPneumatiques(){ return DepotPneumatiques;}
	public boolean getImpactPlomb(){ return ImpactPlomb;}
	public boolean getConsommateurCOV(){ return ConsommateurCOV;} //sites consommant plus de 30 tonnes de COV.
	public boolean getSoumisGEREP(){ return SoumisGEREP;}
	public boolean getSoumisRSDE2004(){ return SoumisRSDE2004;} //soumis réduction réelle substances dangereuses eau après 2004.
	public boolean getSoumisREISTA(){ return SoumisREISTA;}
	public boolean getPlanModernisation(){ return PlanModernisation;} //AM du 4 oct 2010.
    public boolean getOuvrageRetenue(){ return OuvrageRetenue;}
    public boolean getOuvrageProtection(){ return OuvrageProtection;}
    public boolean getEpandages(){ return Epandages;}
    public boolean getSoumisTGAPAir(){ return SoumisTGAPAir;}
    public boolean getCHSCT(){ return CHSCT;}
    public boolean getMilieu(){ return Milieu;}
    public boolean getPollutionAccidentelle(){ return PollutionAccidentelle;}
    public boolean getEMASISO14001(){ return EMASISO14001;}
    public String getDateFinEMASISO14001(){ return DateFinEMASISO14001;}
    public boolean getSecheresse(){ return Secheresse;}
    public boolean getRemiseEtat(){ return RemiseEtat;}
    public boolean getCarriere(){ return Carriere;}
    public boolean getGarantiesFinancieres(){ return GarantiesFinancieres;}
    public boolean getResponsableDefaillant(){ return ResponsableDefaillant;}
    public boolean getElevage(){ return Elevage;}
    public boolean getPreventionLegionellose(){ return PreventionLegionellose;}
    public String getNumEDE(){ return NumEDE;}
    public String getNumPACAGE(){ return NumPACAGE;}
    public String getVieillissement(){ return Vieillissement;}
    public boolean getSoumisPOI(){ return SoumisPOI;}
    public String getDateMAJPOI(){ return DateMAJPOI;}
    public boolean getSoumisPAC(){ return SoumisPAC;}
    public String getDatePAC(){ return DatePAC;}
    public boolean getMaitriseUrbanismePIGSUP(){ return MaitriseUrbanismePIGSUP;}
    public boolean getNI140(){ return NI140;}
    
    
    private boolean ReadBoolean(String readString)
    {
    	if(readString.equals("Oui"))
    	{
    		return true;
    	} 
    	else 
    	{
    		return false;
    	}
    		
    }
 
	
}
