package com.gigaspaces;

import com.gigaspaces.model.Company;
import com.gigaspaces.model.Foods;
import com.j_spaces.core.IJSpace;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import java.util.logging.Logger;

/**
 * Created by kobi on 12/10/14.
 */
public class JoinTwoTables {

    public static Logger logger = Logger.getLogger("Process");
    public static final String url = "/./mySpace";

    static IJSpace space = null;
    static GigaSpace gigaSpace = null;

    public static void main(String[] args) {
        space = new UrlSpaceConfigurer(url).space();
        gigaSpace = new GigaSpaceConfigurer(space).gigaSpace();


//      create and write foodses objects

//        +---------+--------------+-----------+------------+
//        | ITEM_ID | ITEM_NAME    | ITEM_UNIT | COMPANY_ID |
//        +---------+--------------+-----------+------------+
//        | 1       | Chex Mix     | Pcs       | 16         |
//        | 6       | Cheez-It     | Pcs       | 15         |
//        | 2       | BN Biscuit   | Pcs       | 15         |
//        | 3       | Mighty Munch | Pcs       | 17         |
//        | 4       | Pot Rice     | Pcs       | 15         |
//        | 5       | Jaffa Cakes  | Pcs       | 18         |
//        | 7       | Salt n Shake | Pcs       | 19         |
//        +---------+--------------+-----------+------------+
        Foods[] foodses = getFoodses();

        gigaSpace.writeMultiple(foodses);

//      create  and write companies
//        +------------+---------------+--------------+
//        | COMPANY_ID | COMPANY_NAME  | COMPANY_CITY |
//        +------------+---------------+--------------+
//        | 18         | Order All     | Boston       |
//        | 15         | Jack Hill Ltd | London       |
//        | 16         | Akas Foods    | Delhi        |
//        | 17         | Foodies.      | London       |
//        | 19         | sip-n-Bite.   | New York     |
//        +------------+---------------+--------------+

        Company[] companies = getCompanies();

        gigaSpace.writeMultiple(companies);

//TODO write the following SQL query eith XAP SQL QUERY - http://docs.gigaspaces.com/xap100net/query-sql.html

//        SELECT foods.item_name,foods.item_unit,
//                company.company_name, company.company_city
//        FROM foods ,company
//        WHERE  foods.company_id =company.company_id
//        AND company.company_city='London';




    }

        private static Company[] getCompanies() {
                String[] companyNames = {"Order All", "Jack Hill Ltd", "Akas Foods", "Foodies.", "sip-n-Bite."};
                String[] companyCities = {"Boston", "London", "Delhi", "London", "New York"};
                Company[] companies = new Company[5];

                Company company = new Company();
                company.setCompanyId(18);
                company.setCompanyCity(companyCities[0]);
                company.setCompanyName(companyNames[0]);
                companies[0] = company;

                company = new Company();
                company.setCompanyId(15);
                company.setCompanyCity(companyCities[1]);
                company.setCompanyName(companyNames[1]);
                companies[1] = company;

                company = new Company();
                company.setCompanyId(16);
                company.setCompanyCity(companyCities[2]);
                company.setCompanyName(companyNames[2]);
                companies[2] = company;

                company = new Company();
                company.setCompanyId(17);
                company.setCompanyCity(companyCities[3]);
                company.setCompanyName(companyNames[3]);
                companies[3] = company;

                company = new Company();
                company.setCompanyId(19);
                company.setCompanyCity(companyCities[4]);
                company.setCompanyName(companyNames[4]);
                companies[4] = company;
                return companies;
        }

        private static Foods[] getFoodses() {
                String[] itemNames = {"Chex Mix", "Cheez-It", "BN Biscuit", "Mighty Munch", "Pot Rice", "Jaffa Cakes", "Salt n Shake"};
                Foods[] foodses = new Foods[7];

                Foods foods = new Foods();
                foods.setItemId(1);
                foods.setItemName(itemNames[0]);
                foods.setItemUnit("Pcs");
                foods.setCompanyId(16);
                foodses[0] = foods;

                foods = new Foods();
                foods.setItemId(2);
                foods.setItemName(itemNames[2]);
                foods.setItemUnit("Pcs");
                foods.setCompanyId(15);
                foodses[1] = foods;

                foods = new Foods();
                foods.setItemId(3);
                foods.setItemName(itemNames[3]);
                foods.setItemUnit("Pcs");
                foods.setCompanyId(17);
                foodses[2] = foods;

                foods = new Foods();
                foods.setItemId(4);
                foods.setItemName(itemNames[4]);
                foods.setItemUnit("Pcs");
                foods.setCompanyId(15);
                foodses[3] = foods;

                foods = new Foods();
                foods.setItemId(5);
                foods.setItemName(itemNames[5]);
                foods.setItemUnit("Pcs");
                foods.setCompanyId(18);
                foodses[4] = foods;

                foods = new Foods();
                foods.setItemId(6);
                foods.setItemName(itemNames[1]);
                foods.setItemUnit("Pcs");
                foods.setCompanyId(15);
                foodses[5] = foods;

                foods = new Foods();
                foods.setItemId(7);
                foods.setItemName(itemNames[6]);
                foods.setItemUnit("Pcs");
                foods.setCompanyId(19);
                foodses[6] = foods;
                return foodses;
        }
}
