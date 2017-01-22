package ICPE;

import java.util.Vector;

import InputOutput.CSVReader;

public class Installation {

	/**
	 *
	 */
	public String S3ICNum;
	public String RubriqueAutorisee;
	public String Alinea;
	public ICPERegime Regime;
	public String Activite;
	public String Nature;
	public String Critere;
	public String CritereSeuil;
	public String Quantite;	
	public String UniteQuantite;	
	public String RubriqueVigueurCorresp;	
	public String RVCAlinea;	
	public ICPERegime RVCRegime;
	public String DateDernierePrescription	;	
	public String DateAutorisationInitiale;	
	public String DateDerniereModificationNotable;
	public String EtatTechnique;
	
	public Installation(){}
	
	public Installation(String CSVLine){
    	Vector<String> VectorLine = CSVReader.ParseCSVLine(CSVLine);
    	this.S3ICNum =VectorLine.get(0);
    	this.RubriqueAutorisee=VectorLine.get(9);
    	this.Alinea=VectorLine.get(10);
    	String ParsedRegime=VectorLine.get(11);
    	if(ParsedRegime.equals("A")||ParsedRegime.equals("S"))
    		{
    		this.Regime = ICPERegime.AUTORISATION;
    		}
    	else if(ParsedRegime.equals("E"))
    		{
    		this.Regime=ICPERegime.ENREGISTREMENT;
    		}
    	else if(ParsedRegime.equals("D")||ParsedRegime.equals("DC"))
    		{
    		this.Regime = ICPERegime.DECLARATION;
    		}
    	else 
    		{
    		this.Regime=ICPERegime.NONCLASSE;
    		}
    	this.Activite=VectorLine.get(12);
    	this.Nature=VectorLine.get(13);
    	this.Critere=VectorLine.get(14);
    	this.CritereSeuil=VectorLine.get(15);
    	this.Quantite=VectorLine.get(16);
    	this.UniteQuantite=VectorLine.get(17);
    	this.RubriqueVigueurCorresp=VectorLine.get(18);
    	this.RVCAlinea=VectorLine.get(19);
    	String ParsedRVCRegime = VectorLine.get(20);
    	if(ParsedRVCRegime.equals("A")||ParsedRVCRegime.equals("S"))
			{
    		this.RVCRegime = ICPERegime.AUTORISATION;
			}
    	else if(ParsedRVCRegime.equals("E"))
			{
    		this.RVCRegime=ICPERegime.ENREGISTREMENT;
			}
    	else if(ParsedRVCRegime.equals("D")||ParsedRVCRegime.equals("DC"))
			{
    		this.RVCRegime = ICPERegime.DECLARATION;
			}
    	else 
			{
    		this.RVCRegime=ICPERegime.NONCLASSE;
			}
    	this.DateDernierePrescription=VectorLine.get(21);
    	this.DateAutorisationInitiale=VectorLine.get(23);
    	this.DateDerniereModificationNotable=VectorLine.get(24);
    	this.EtatTechnique = VectorLine.get(39);
	}
	
	public String getS3ICNum(){return S3ICNum;}
	public String getRubriqueAutorisee(){return RubriqueAutorisee;}
	public String getAlinea(){return Alinea;}
	public ICPERegime getRegime(){return Regime;}
	public String getActivite(){return Activite;}
	public String getNature(){return Nature;}
	public String getCritere(){return Critere;}
	public String getCritereSeuil(){return CritereSeuil;}
	public String getQuantite(){return Quantite;}
	public String getUniteQuantite(){return UniteQuantite;}	
	public String getRubriqueVigueurCorresp(){return RubriqueVigueurCorresp;}	
	public String getRVCAlinea(){return RVCAlinea;}
	public ICPERegime getRVCRegime(){return RVCRegime;}
	public String getDateDernierePrescription(){return DateDernierePrescription;}
	public String getDateAutorisationInitiale(){return DateAutorisationInitiale;}
	public String getDateDerniereModificationNotable(){return DateDerniereModificationNotable;}
	public String getEtatTechnique(){return EtatTechnique;}
}
