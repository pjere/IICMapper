package ICPE;

public enum ICPERegime {
 AUTORISATION,
 ENREGISTREMENT,
 SEVESOHAUT,
 SEVESOBAS,
 NONCLASSE,
 DECLARATION;
 
 public String toString()
 {
	switch(this){
	case AUTORISATION: return "A";
	case DECLARATION: return "D";
	case ENREGISTREMENT: return "E";
	case NONCLASSE: return "NC";
	case SEVESOBAS: return "SB";
	case SEVESOHAUT: return "SH";
	default: return "NC";
	}
		 
 }

}
