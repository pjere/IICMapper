package ICPE;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import Geography.GeoCode;
import Geography.GeoCodeStatus;
import Geography.Lambert93;
import InputOutput.CSVReader;


public class Site {


	private String S3ICNum; //0
	private String NomUsuel; //1
	private String RaisonSociale; //2
	private ICPERegime RegimeEtablissement; //4 et 5
	private String Commune; //6
	private String CodeINSEE; //7
	private String CodePostal; //8
	private String Adresse; //9 et 10 puis 8 puis 6
	private String Telephone; // 11
	private String Fax;//12
	private String Departement;// avec 7
	private String ServiceControle; //14
	private String AdresseAdministrative; //Adresse pour la facturation TGAP, etc. 15 16 17 18 19
	private String SIRET;//31 
	private double Latitude;
	private double Longitude;
	private String NAFCode;//36
	private boolean SoumisRemiseEtat;//38
	private boolean EstPrioritaire;//39
	private boolean EstAEnjeux;//40
	private boolean CritereEau;//41
	private boolean CritereAir;//42
	private boolean CritereDechets;//43
	private boolean CritereSSP;//44
	private boolean CritereRisques;//45
	private boolean SoumisQuotas;//46
	private boolean SoumisGIC;//47
	private boolean SoumisIEDGIC;//48
	private boolean EstIncinerateur;//50
	private boolean SoumisReductionCOV;//51
	private boolean STEPIndustrielle;//52
	private boolean SoumisIPPC;//53
	private boolean IED;//54 attention il faut caster IED-MTD ou nonIED-MTD en boolean
	private boolean RejetsAqueux;//55
	private boolean RejetsAtmospheriques;//56
	private boolean DeclarationDechets;//57
	private boolean AutosurveillanceEauxSouterraines;//58
	private boolean SurveillanceMilieux;//59
	private boolean DeclarationEmissions;//61
	private boolean SoumisRSDE2002;//62
	private boolean SoumisRSDE2009;//63
	private boolean Amiante; //sites ayant utilisé de l'amiante.64
	private boolean DepotPneumatiques;//65
	private boolean ImpactPlomb;//66
	private boolean ConsommateurCOV; //sites consommant plus de 30 tonnes de COV.67
	private boolean SoumisGEREP;//68
	private boolean SoumisRSDE2004; //69 soumis réduction réelle substances dangereuses eau après 2004.
	private boolean SoumisREISTA;//70
	private boolean PlanModernisation; //71 AM du 4 oct 2010.
    private boolean OuvrageRetenue;//72
    private boolean OuvrageProtection;//73
    private boolean Epandages;//74
    private boolean SoumisTGAPAir;//75
    private boolean CHSCT;//76
    private boolean Milieu;//77
    private boolean PollutionAccidentelle;//78
    private boolean EMASISO14001;//80
    private String DateFinEMASISO14001;//81
    private boolean Secheresse;//82
    private boolean RemiseEtat;//83
    private boolean Carriere;//84
    private boolean GarantiesFinancieres;//85
    private boolean ResponsableDefaillant;//86
    private boolean Elevage;//87
    private boolean PreventionLegionellose;//88
    private String NumEDE;//91
    private String NumPACAGE;//92
    private String Vieillissement;//95
    private boolean SoumisPOI;//107
    private String DateMAJPOI;//108
    private boolean SoumisPAC;//109
    private String DatePAC;//110
    private boolean MaitriseUrbanismePIGSUP;//111
    private boolean NI140;//93
    @SuppressWarnings("unused")
	private HashSet<Installation> TableauClassement;
    @SuppressWarnings("unused")
	private HashMap<String,Double> GEREPAir;
    
    public Site(){}
    
    public Site(String CSVLine){
    	Vector<String> VectorLine = CSVReader.ParseCSVLine(CSVLine);
    	
    	this.TableauClassement = new HashSet<Installation>();
    	this.GEREPAir = new HashMap<String,Double>();
    	
    	this.S3ICNum=VectorLine.get(0); //0
    	this.NomUsuel=VectorLine.get(1); //1
    	this.RaisonSociale=VectorLine.get(2); //2
    	String Regime = VectorLine.get(4);
    	String SevesoStatus=VectorLine.get(5);
    	if(Regime.equals("E"))
    			{this.RegimeEtablissement=ICPERegime.ENREGISTREMENT;}
    	else if (Regime.equals("A"))
    			{
    				if(SevesoStatus.equals("NS - Non Seveso"))
    				{this.RegimeEtablissement=ICPERegime.AUTORISATION;}
    				else if (SevesoStatus.equals("SSB - Seuil Bas (Seveso III)"))
    				{this.RegimeEtablissement=ICPERegime.SEVESOBAS;}
    				else if (SevesoStatus.equals("SSH - Seuil Haut (Seveso III)"))
    				{this.RegimeEtablissement=ICPERegime.SEVESOBAS;}
    			}//4 et 5
    	this.Commune = VectorLine.get(6); //6
    	this.CodeINSEE = VectorLine.get(7); //7
    	this.CodePostal = VectorLine.get(8); //8
    	this.Adresse= VectorLine.get(9)+", "+VectorLine.get(10)+", "+VectorLine.get(8)+", "+VectorLine.get(6)+", France"; //9 et 10 puis 8 puis 6
    	this.Telephone = VectorLine.get(11); // 11
    	this.Fax = VectorLine.get(12);//12
    	this.Departement= VectorLine.get(7).substring(0,2);// avec 7
    	this.ServiceControle= VectorLine.get(14); //14
    	this.AdresseAdministrative = VectorLine.get(15)+", "+VectorLine.get(16)+", "+VectorLine.get(17)+", "+VectorLine.get(18)+", "+VectorLine.get(19); //Adresse pour la facturation TGAP, etc. 15 16 17 18 19
    	this.SIRET=VectorLine.get(31);//31 
    	int Lambert93X = Integer.parseInt(VectorLine.get(33)) ;
    	int Lambert93Y = Integer.parseInt(VectorLine.get(34)) ;
    	Point2D.Double myLatLon = new Point2D.Double(0,0);
    	if(VectorLine.get(35).equals("Coordonnée précise"))
    	{
    		Lambert93.toLatLon(Lambert93X, Lambert93Y, myLatLon);
        	this.Latitude=myLatLon.y;
        	this.Longitude=myLatLon.x;
    	}
    	else if (VectorLine.get(35).equals("Adresse postale"))
    	{
    		Lambert93.toLatLon(Lambert93X, Lambert93Y, myLatLon);
        	this.Latitude=myLatLon.y;
        	this.Longitude=myLatLon.x;
    	}
    	else
    	{
    		GeoCode myGeoCode = new GeoCode(this.Adresse);
    		if(!myGeoCode.getStatus().equals(GeoCodeStatus.GEOCODE_OK))
    		{
        		Lambert93.toLatLon(Lambert93X, Lambert93Y, myLatLon);
            	this.Latitude=myLatLon.y;
            	this.Longitude=myLatLon.x;
    		}
    		else
    		{
    			this.Latitude = myGeoCode.getNumLatitude();
    			this.Longitude = myGeoCode.getNumLongitude();
    		}
    	}

    	this.NAFCode=VectorLine.get(36);//36
    	this.SoumisRemiseEtat=ReadBoolean(VectorLine.get(38));//38
    	this.EstPrioritaire=ReadBoolean(VectorLine.get(39));//39
    	this.EstAEnjeux=ReadBoolean(VectorLine.get(40));//40
    	this.CritereEau=ReadBoolean(VectorLine.get(41));//41
    	this.CritereAir=ReadBoolean(VectorLine.get(42));//42
    	this.CritereDechets=ReadBoolean(VectorLine.get(43));//43
    	this.CritereSSP=ReadBoolean(VectorLine.get(44));//44
    	this.CritereRisques=ReadBoolean(VectorLine.get(45));//45
    	this.SoumisQuotas=ReadBoolean(VectorLine.get(46));//46
    	this.SoumisGIC=ReadBoolean(VectorLine.get(47));//47
    	this.SoumisIEDGIC=ReadBoolean(VectorLine.get(48));//48
    	this.EstIncinerateur=ReadBoolean(VectorLine.get(50));//50
    	this.SoumisReductionCOV=ReadBoolean(VectorLine.get(51));//51
    	this.STEPIndustrielle=ReadBoolean(VectorLine.get(52));//52
    	this.SoumisIPPC=ReadBoolean(VectorLine.get(53));//53
    	if(VectorLine.get(54).equals("Non IED - MTD")){this.IED=false;} else {this.IED=true;};//54 attention il faut caster IED-MTD ou nonIED-MTD en boolean
    	this.RejetsAqueux=ReadBoolean(VectorLine.get(55));//55
    	this.RejetsAtmospheriques=ReadBoolean(VectorLine.get(56));//56
    	this.DeclarationDechets=ReadBoolean(VectorLine.get(57));//57
    	this.AutosurveillanceEauxSouterraines=ReadBoolean(VectorLine.get(38));//58
    	this.SurveillanceMilieux=ReadBoolean(VectorLine.get(59));//59
    	this.DeclarationEmissions=ReadBoolean(VectorLine.get(61));//61
    	this.SoumisRSDE2002=ReadBoolean(VectorLine.get(62));//62
    	this.SoumisRSDE2009=ReadBoolean(VectorLine.get(63));//63
    	this.Amiante=ReadBoolean(VectorLine.get(64)); //sites ayant utilisé de l'amiante.64
    	this.DepotPneumatiques=ReadBoolean(VectorLine.get(65));//65
    	this.ImpactPlomb=ReadBoolean(VectorLine.get(66));//66
    	this.ConsommateurCOV=ReadBoolean(VectorLine.get(67)); //sites consommant plus de 30 tonnes de COV.67
    	this.SoumisGEREP=ReadBoolean(VectorLine.get(68));//68
    	this.SoumisRSDE2004=ReadBoolean(VectorLine.get(69)); //69 soumis réduction réelle substances dangereuses eau après 2004.
    	this.SoumisREISTA=ReadBoolean(VectorLine.get(70));//70
    	this.PlanModernisation=ReadBoolean(VectorLine.get(71)); //71 AM du 4 oct 2010.
        this.OuvrageRetenue=ReadBoolean(VectorLine.get(72));//72
        this.OuvrageProtection=ReadBoolean(VectorLine.get(73));//73
        this.Epandages=ReadBoolean(VectorLine.get(74));//74
        this.SoumisTGAPAir=ReadBoolean(VectorLine.get(75));//75
        this.CHSCT=ReadBoolean(VectorLine.get(76));//76
        this.Milieu=ReadBoolean(VectorLine.get(77));//77
        this.PollutionAccidentelle=ReadBoolean(VectorLine.get(78));//78
        this.EMASISO14001=ReadBoolean(VectorLine.get(80));//80
        this.DateFinEMASISO14001=VectorLine.get(81);//81
        this.Secheresse=ReadBoolean(VectorLine.get(82));//82
        this.RemiseEtat=ReadBoolean(VectorLine.get(83));//83
        this.Carriere=ReadBoolean(VectorLine.get(84));//84
        this.GarantiesFinancieres=ReadBoolean(VectorLine.get(85));//85
        this.ResponsableDefaillant=ReadBoolean(VectorLine.get(86));//86
        this.Elevage=ReadBoolean(VectorLine.get(87));//87
        this.PreventionLegionellose=ReadBoolean(VectorLine.get(88));//88
        this.NumEDE=VectorLine.get(91);//91
        this.NumPACAGE=VectorLine.get(92);//92
        this.Vieillissement=VectorLine.get(95);//95
        this.SoumisPOI=ReadBoolean(VectorLine.get(107));//107
        this.DateMAJPOI=VectorLine.get(108);//108
        this.SoumisPAC=ReadBoolean(VectorLine.get(109));//109
        this.DatePAC=VectorLine.get(110);//110
        this.MaitriseUrbanismePIGSUP=ReadBoolean(VectorLine.get(111));//111
        this.NI140=ReadBoolean(VectorLine.get(38));//93
    	
    	
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
