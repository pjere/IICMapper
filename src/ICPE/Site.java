package ICPE;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import Geography.GeoCode;
import Geography.GeoCodeStatus;
import Geography.Lambert93;
import InputOutput.CSVReader;
import InputOutput.MyFileWriter;


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
	private HashSet<Installation> TableauClassement;
	private HashMap<String,Double> GEREPAir;
	private HashMap<String,Double> GEREPEau;
	private HashMap<String,String> GEREPEauType;
    
    public Site(){}
    
    public Site(String CSVLine, String TableauClassementFileAddress, String GEREPAirFileAddress, String GEREPEauFileAddress){
    	Vector<String> VectorLine = CSVReader.ParseCSVLine(CSVLine);
    	this.S3ICNum=VectorLine.get(0); //0
    	String S3ICNumInitial = S3ICNum.substring(0, S3ICNum.indexOf("."));
    	String S3ICNumFinal = S3ICNum.substring(S3ICNum.indexOf(".")+1,S3ICNum.length());
    	int S3ICNumInitialInt= Integer.parseInt(S3ICNumInitial);
    	this.S3ICNum = S3ICNumInitialInt+"."+S3ICNumFinal;
    	BufferedReader rdr ;
    	this.TableauClassement = new HashSet<Installation>();
		try
		{
			rdr = new BufferedReader(new FileReader(TableauClassementFileAddress)) ;
			Installation Current = new Installation();
			while (rdr.ready()==true) 
			{
				Current = new Installation(rdr.readLine());
				if(Current.getS3ICNum().equals(this.S3ICNum)){TableauClassement.add(Current);}
			}//while
		}//try
		catch (NullPointerException a)
		{
			a.printStackTrace();
		}
		catch (IOException a) 
		{
			System.out.println("Problème d'IO");
		}
    	this.GEREPAir = new HashMap<String,Double>();
    	
    	try
		{
			rdr = new BufferedReader(new FileReader(GEREPAirFileAddress)) ;
			Vector<String> CurrentLine = new Vector<String>();
			String str ="";
			while (rdr.ready()==true) 
			{
				str = rdr.readLine();
				//System.out.println(str);
				CurrentLine = CSVReader.ParseCSVLine(str);
				if(CurrentLine.get(1).equals(this.S3ICNum))
				{
					GEREPAir.put(CurrentLine.get(9), Double.parseDouble(CurrentLine.get(13)));
				}
			}//while
		}//try
		catch (NullPointerException a)
		{
			a.printStackTrace();
		}
		catch (IOException a) 
		{
			System.out.println("Problème d'IO");
		}
    	
    	
    	this.GEREPEau = new HashMap<String,Double>();
    	this.GEREPEauType = new HashMap<String,String>();
    	
    	try
		{
			rdr = new BufferedReader(new FileReader(GEREPEauFileAddress)) ;
			Vector<String> CurrentLine = new Vector<String>();
			String str ="";
			while (rdr.ready()==true) 
			{
				str = rdr.readLine();
				//System.out.println(str);
				CurrentLine = CSVReader.ParseCSVLine(str);
				if(CurrentLine.get(1).equals(this.S3ICNum))
				{
					GEREPEau.put(CurrentLine.get(9), Double.parseDouble(CurrentLine.get(11)));
					GEREPEauType.put(CurrentLine.get(9), CurrentLine.get(10));
				}
			}//while
		}//try
		catch (NullPointerException a)
		{
			a.printStackTrace();
		}
		catch (IOException a) 
		{
			System.out.println("Problème d'IO");
		}
    	
    	
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
    				else if (SevesoStatus.contains("SB - Seuil Bas"))
    				{this.RegimeEtablissement=ICPERegime.SEVESOBAS;}
    				else if (SevesoStatus.contains("SH - Seuil Haut"))
    				{this.RegimeEtablissement=ICPERegime.SEVESOHAUT;}
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
    	try
    	{
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
    	}
    	catch(Exception NumberFormatException)
    	{
        	Point2D.Double myLatLon = new Point2D.Double(0,0);
        	GeoCode myGeoCode = new GeoCode(this.CodePostal+", "+this.Commune+", FRANCE");
    		if(!myGeoCode.getStatus().equals(GeoCodeStatus.GEOCODE_OK))
    		{
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
    
    
    private static boolean ReadBoolean(String readString)
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
    
    
    public String toHTML()
    {
    	String result = "";
    	result = result+"<b>"+MyFileWriter.escape(this.RaisonSociale)+"</b><br><br>";

    	result = result+"<b>NAFCode      </b> : "+this.NAFCode+"<br>";
    	result = result+"<b>N&deg; S3IC      </b> : "+this.S3ICNum+"<br>";
    	result = result+"<b>Régime       </b> : "+this.RegimeEtablissement.toString()+"<br><hr><br>";
    	result = result+"<b>Commune      </b> : "+MyFileWriter.escape(this.Commune)+"<br>";
    	result = result+"<b>CodeINSEE    </b> : "+this.CodeINSEE+"<br>";
    	result = result+"<b>CodePostal   </b> : "+this.CodePostal+"<br>";
    	result = result+"<b>"+MyFileWriter.escape("Département")+"  </b> : "+this.Departement+"<br>";
    	result = result+"<b>Telephone    </b> : "+this.Telephone+"<br>";
    	result = result+"<b>Fax          </b> : "+this.Fax+"<br>";
    	result = result+"<b>Adresse      </b> : "+MyFileWriter.escape(this.Adresse)+"<br>";
    	result = result+"<b>SIRET        </b> : "+this.SIRET+"<br>";
    	result = result+"<b>Latitude     </b> : "+this.Latitude+"<br>";
    	result = result+"<b>Longitude    </b> : "+this.Longitude+"<br><hr><br>";
    	
    	result = result+"<b>Tableau de Classement</b><br><br>";
    	result = result+"<table style=\"width:100%\"><tr><th>Rubrique</th><th>"+MyFileWriter.escape("Alinéa")+"</th><th>"+MyFileWriter.escape("Régime")+"</th><th>Date Dern. Mod. Notable</th><th>"+MyFileWriter.escape("État technique")+"</th></tr>";
    	for(Installation i : this.TableauClassement)
    	{
    		if(i.EtatTechnique.equals("En fonctionnement"))
    		{
    		result = result+"<tr><td>"+i.RubriqueVigueurCorresp+"</td><td>"+i.RVCAlinea+"</td><td>"+i.RVCRegime.toString()+"</td><td>"+i.DateDerniereModificationNotable+"</td><td>"+MyFileWriter.escape(i.EtatTechnique)+"</td><tr>";
    		}
    	}
    	result = result+"</table><br><hr><br>";
    	
    	result = result+"<b>"+MyFileWriter.escape("Déclaration")+" GEREP Air</b><br><br>";
    	result = result+"<table style=\"width:100%\"><tr><th>"+MyFileWriter.escape("Paramètre")+"</th><th>Rejets AN-1 (kg)</th></tr>";
    	for(String i : this.GEREPAir.keySet())
    	{
    		result = result+"<tr><td>"+MyFileWriter.escape(i)+"</td><td>"+this.GEREPAir.get(i)+"</td><tr>";
    	}
    	result = result+"</table><br><hr><br>";
    	
    	result = result+"<b>"+MyFileWriter.escape("Déclaration")+" GEREP Eau</b><br><br>";
    	result = result+"<table style=\"width:100%\"><tr><th>"+MyFileWriter.escape("Paramètre")+"</th><th>Rejets AN-1 (kg)</th><th>Type Rejet</th></tr>";
    	for(String i : this.GEREPEau.keySet())
    	{
    		result = result+"<tr><td>"+MyFileWriter.escape(i)+"</td><td>"+this.GEREPEau.get(i)+"</td><td>"+this.GEREPEauType.get(i)+"</td><tr>";
    	}
    	result = result+"</table><br><hr><br>";
    	
    	
    	
    	result = result+"<b> Autres Critères</b><br><br>";		

	result = result + "SoumisRemiseEtat : "+SoumisRemiseEtat+"<br>";//38
	result = result + "EstPrioritaire : "+EstPrioritaire+"<br>";//39
	result = result + "EstAEnjeux : "+EstAEnjeux+"<br>";//40
	result = result + "CritereEau : "+CritereEau+"<br>";//41
	result = result + "CritereAir : "+CritereAir+"<br>";//42
	result = result + "CritereDechets : "+CritereDechets+"<br>";//43
	result = result + "CritereSSP : "+CritereSSP+"<br>";//44
	result = result + "CritereRisques : "+CritereRisques+"<br>";//45
	result = result + "SoumisQuotas : "+SoumisQuotas+"<br>";//46
	result = result + "SoumisGIC : "+SoumisGIC+"<br>";//47
	result = result + "SoumisIEDGIC : "+SoumisIEDGIC+"<br>";//48
	result = result + "EstIncinerateur : "+EstIncinerateur+"<br>";//50
	result = result + "SoumisReductionCOV : "+SoumisReductionCOV+"<br>";//51
	result = result + "STEPIndustrielle : "+STEPIndustrielle+"<br>";//52
	result = result + "SoumisIPPC : "+SoumisIPPC+"<br>";//53
	result = result + "IED : "+IED+"<br>";//54 attention il faut caster IED-MTD ou nonIED-MTD en boolean
	result = result + "RejetsAqueux : "+RejetsAqueux+"<br>";//55
	result = result + "RejetsAtmospheriques : "+RejetsAtmospheriques+"<br>";//56
	result = result + "DeclarationDechets : "+DeclarationDechets+"<br>";//57
	result = result + "AutosurveillanceEauxSouterraines : "+AutosurveillanceEauxSouterraines+"<br>";//58
	result = result + "SurveillanceMilieux : "+SurveillanceMilieux+"<br>";//59
	result = result + "DeclarationEmissions : "+DeclarationEmissions+"<br>";//61
	result = result + "SoumisRSDE2002 : "+SoumisRSDE2002+"<br>";//62
	result = result + "SoumisRSDE2009 : "+SoumisRSDE2009+"<br>";//63
	result = result + "Amiante : "+Amiante+"<br>"; //sites ayant utilisé de l'amiante.64
	result = result + "DepotPneumatiques : "+DepotPneumatiques+"<br>";//65
	result = result + "ImpactPlomb : "+ImpactPlomb+"<br>";//66
	result = result + "ConsommateurCOV : "+ConsommateurCOV+"<br>"; //sites consommant plus de 30 tonnes de COV.67
	result = result + "SoumisGEREP : "+SoumisGEREP+"<br>";//68
	result = result + "SoumisRSDE2004 : "+SoumisRSDE2004+"<br>"; //69 soumis réduction réelle substances dangereuses eau après 2004.
	result = result + "SoumisREISTA : "+SoumisREISTA+"<br>";//70
	result = result + "PlanModernisation : "+PlanModernisation+"<br>"; //71 AM du 4 oct 2010.
    result = result + "OuvrageRetenue : "+OuvrageRetenue+"<br>";//72
    result = result + "OuvrageProtection : "+OuvrageProtection+"<br>";//73
    result = result + "Epandages : "+Epandages+"<br>";//74
    result = result + "SoumisTGAPAir : "+SoumisTGAPAir+"<br>";//75
    result = result + "CHSCT : "+CHSCT+"<br>";//76
    result = result + "Milieu : "+Milieu+"<br>";//77
    result = result + "PollutionAccidentelle : "+PollutionAccidentelle+"<br>";//78
    result = result + "EMASISO14001 : "+EMASISO14001+"<br>";//80
    result = result + "DateFinEMASISO14001 : "+DateFinEMASISO14001+"<br>";//81
    result = result + "Secheresse : "+Secheresse+"<br>";//82
    result = result + "RemiseEtat : "+RemiseEtat+"<br>";//83
    result = result + "Carriere : "+Carriere+"<br>";//84
    result = result + "GarantiesFinancieres : "+GarantiesFinancieres+"<br>";//85
    result = result + "ResponsableDefaillant : "+ResponsableDefaillant+"<br>";//86
    result = result + "Elevage : "+Elevage+"<br>";//87
    result = result + "PreventionLegionellose : "+PreventionLegionellose+"<br>";//88
    result = result + "NumEDE : "+NumEDE+"<br>";//91
    result = result + "NumPACAGE : "+NumPACAGE+"<br>";//92
    result = result + "Vieillissement : "+Vieillissement+"<br>";//95
    result = result + "SoumisPOI : "+SoumisPOI+"<br>";//107
    result = result + "DateMAJPOI : "+DateMAJPOI+"<br>";//108
    result = result + "SoumisPAC : "+SoumisPAC+"<br>";//109
    result = result + "DatePAC : "+DatePAC+"<br>";//110
    result = result + "MaitriseUrbanismePIGSUP : "+MaitriseUrbanismePIGSUP+"<br>";//111
    result = result + "NI140 : "+NI140+"<br>";//93
  	
    	return result;
    }
    
 
	
}
