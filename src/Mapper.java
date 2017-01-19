import ICPE.Site;


public class Mapper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Site TestInsta = new Site("0065.10767	ONERA	ONERA	En fonctionnement	A	NS - Non Seveso	MEUDON	92048	92190	8 rue des Vertugadins				HAUTS DE SEINE	CGA	29 avenue de la Division Leclerc	BP 72	92322	CHATILLON	FRANCE												77572287900027		643946	6855898	Valeur Initiale	72.19Z	R&D : aut. sciences physique & naturelle	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non IED - MTD	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non	Non		Non	Non	Non	Non	Non	Non	Non					Non						Non	Non	Non						Non		Non		Non");
	 System.out.println(TestInsta.getAdresse());
	 System.out.println(TestInsta.getDepartement());
	 System.out.println(TestInsta.getAmiante()); 
	 System.out.println(TestInsta.getNAFCode());
	 System.out.println(TestInsta.getS3ICNum());
	 System.out.println(TestInsta.getLatitude());
	 System.out.println(TestInsta.getLongitude());
	}

}
