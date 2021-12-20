 package com.example.menu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBSqlite extends SQLiteOpenHelper {
    private static DBSqlite sInstance;
    private Context context;
    public static final String DB_NAME ="My_Menu.db";
    public static final  String Table_NameF = "restaurent";
    public static final  String Table_NameD1 = "category";
    public static final  String Table_NameD2 = "option";
    public static final  int DB_version = 1;
    public static final  String Column_id = "_id";
    public static final  String Column_Nom = "nomRest";
    public static final  String Column_Description = "descreption";
    public static final  String Column_Adresse = "adresse";
    public static final  String Column_Telephone = "telephone";
    public static String FAVORITE_STATUS = "fStatus";
    public static final  String Column_Option = "option";
    public static final  String Column_Image = "image";
    public static final  String Column_Menu_img = "menu_img";
    public static final  String Column_Reduction = "reduction";
    public static final  String Column_Category = "category";
    public static final  String Column_Speciality = "speciality";
    public static final  String Column_id_op = "_id";
    public static final  String Column_id_cat = "_id";
    public static final  String Column_Nom_cat = "nomCat";
    public static final  String Column_Nom_op = "nomOpt";

    /*public static synchronized DBSqlite getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            sInstance = new DBSqlite(context.getApplicationContext());
            //sInstance.addData();
        }
        return sInstance;
    }*/

    public DBSqlite(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
        Log.d("const","in middle of construction");
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("creat","in middle of construction");
        String queryD1 = "create table "+Table_NameD1+"("+Column_id_cat+" Integer primary key AUTOINCREMENT,"+Column_Nom_cat+" Text"+");";
        String queryD2 = "create table "+Table_NameD2+"("+Column_id_op+" Integer primary key AUTOINCREMENT,"+Column_Nom_op+" Text"+");";
        String queryF  = "create table "+Table_NameF+"("+Column_id+" Integer primary key AUTOINCREMENT,"+Column_Nom+" Text,"+Column_Nom_cat+" Text,"+Column_Description+ FAVORITE_STATUS+" TEXT,"+" Text,"+Column_Adresse+" Text,"+Column_Telephone+" Text,"+Column_Option+" Text,"+Column_Image+" Text,"+Column_Menu_img+" Text,"+Column_Reduction+" Text,"+Column_Category+" Text,"+Column_Speciality+" Text,"+"FOREIGN KEY("+Column_Option+") REFERENCES "+Table_NameD2+"("+Column_id_op+"),"+"FOREIGN KEY("+Column_Nom_cat+") REFERENCES "+Table_NameD1+"("+Column_id_cat+"));";
        db.execSQL(queryD1);
        db.execSQL(queryD2);
        db.execSQL(queryF);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_NameF);
        db.execSQL("DROP TABLE IF EXISTS "+Table_NameD1);
        db.execSQL("DROP TABLE IF EXISTS "+Table_NameD2);
        onCreate(db);

    }
    void addData(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_Nom_cat,"FAST-FOOD");
        db.insert(Table_NameD1,null,cv);
        cv.put(Column_Nom_cat,"ITALIEN");
        db.insert(Table_NameD1,null,cv);
        cv.put(Column_Nom_cat,"COFFE");
        db.insert(Table_NameD1,null,cv);
        cv.put(Column_Nom_cat,"ASIAN");
        db.insert(Table_NameD1,null,cv);
        ContentValues cv1 = new ContentValues();
        cv1.put(Column_Nom_op,"Sur place");
        db.insert(Table_NameD2,null,cv1);
        cv1.put(Column_Nom_op,"A emporter");
        db.insert(Table_NameD2,null,cv1);
        cv1.put(Column_Nom_op,"Traiteur");
        db.insert(Table_NameD2,null,cv1);
        ContentValues cv2 = new ContentValues();
        cv2.put(Column_Nom,"Le Gouthé");
        cv2.put(Column_Nom_cat,"COFFE");
        cv2.put(Column_Description,"Cuisine maison, plats raffinés et classiques, ambiance décontractée et conviviale, café familial.");
        cv2.put(Column_Adresse,"Villa 92 , quartier mabroka (abwab gueliz)\n" +
                "Marrakech");
        cv2.put(Column_Telephone,"0661965115");
        cv2.put(Column_Option,"Sur place");
        cv2.put(Column_Image,"gouth");
        cv2.put(Column_Menu_img,"src/main/res/drawable-v24/gouth1.jpg");
        cv2.put(Column_Reduction,"0%");
        cv2.put(Column_Category,"COFFE");
        Log.d("middle","in middle of creation");
        cv2.put(Column_Speciality,"Petit-déjeuner, Café, Pizza, Tacos, Crêpes");
        db.insert(Table_NameF,null,cv2);
        ContentValues cv3 = new ContentValues();
        cv3.put(Column_Nom,"Allo mama");
        cv3.put(Column_Nom_cat,"ITALIEN");
        cv3.put(Column_Description,"Cuisine maison, plats raffinés et classiques, ambiance décontractée et conviviale, café familial.");
        cv3.put(Column_Adresse,"Residence Rayhana Imm LC5 MAG 36\n" +
                "(En face centre mabrouka de langues)\n" +
                "Marrakech");
        cv3.put(Column_Telephone,"0525006603");
        cv3.put(Column_Option,"Sur place");
        cv3.put(Column_Image,"coffee03");
        cv3.put(Column_Menu_img,"gouth1");
        cv3.put(Column_Reduction,"20%");
        cv3.put(Column_Category,"ITALIEN");
        Log.d("middle","in middle of creation");
        cv3.put(Column_Speciality,"Italien, Pâtes, Pizza");
        db.insert(Table_NameF,null,cv3);
        ContentValues cv4 = new ContentValues();
        cv4.put(Column_Nom,"Zatchi sushi");
        cv4.put(Column_Nom_cat,"ASIAN");
        cv4.put(Column_Description,"Asian modern cuisine");
        cv4.put(Column_Adresse,"Rue oummo El banine, N1\n" +
                "Marrakech");
        cv4.put(Column_Telephone,"05244-34343");
        cv4.put(Column_Option,"Sur place");
        cv4.put(Column_Image,"coffee02");
        cv4.put(Column_Menu_img,"src/main/res/drawable-xxxhdpi/coffee03.jpg");
        cv4.put(Column_Reduction,"15%");
        cv4.put(Column_Category,"ASIAN");
        cv4.put(FAVORITE_STATUS,1);
        Log.d("middle","in middle of creation");
        cv4.put(Column_Speciality,"Sushis, Desserts, Nems");
        db.insert(Table_NameF,null,cv4);
        ContentValues cv5 = new ContentValues();
        cv5.put(Column_Nom,"Café Lobo");
        cv5.put(Column_Nom_cat,"COFFE");
        cv5.put(Column_Description,"Cuisine maison, plats raffinés et classiques, ambiance décontractée et conviviale, café familial.");
        cv5.put(Column_Adresse,"Imm N°1 Rés Les Oliviers 2, Avenue Mohamed VI\n" +
                "Marrakech");
        cv5.put(Column_Telephone,"05244-22422");
        cv5.put(Column_Option,"Sur place");
        cv5.put(Column_Image,"gouth");
        cv5.put(Column_Menu_img,"src/main/res/drawable-v24/gouth1.jpg");
        cv5.put(Column_Reduction,"10%");
        cv5.put(Column_Category,"COFFE");
        Log.d("middle","in middle of creation");
        cv5.put(Column_Speciality,"Café, Petit-déjeuner, Pizza, Tapas");
        long result = db.insert(Table_NameF,null,cv5);
        if(result == -1){
            Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Added successfully!",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readData(){
        String query = "SELECT * FROM "+Table_NameF;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    // remove line from database
    public void remove_fav(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + Table_NameF + " SET  "+ FAVORITE_STATUS+" ='0' WHERE "+Column_id+"="+id+"";
        db.execSQL(sql);
        Log.d("remove", id.toString());

    }

    // select all favorite list

    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+Table_NameF+" WHERE "+FAVORITE_STATUS+" ='1'";
        return db.rawQuery(sql,null,null);
    }


}
