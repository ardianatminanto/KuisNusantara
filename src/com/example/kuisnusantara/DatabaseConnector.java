package com.example.kuisnusantara;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseConnector {
	private SQLiteDatabase database;
	
	private static final String namaDb = "KuisNusantara.db";
	private static final String tabelProv = "provinsi";
	private static final String tabelGambar = "gambar";
	
	public DatabaseConnector(Context context){
		if(!databaseExist(context)){
			String query = 
					"CREATE TABLE IF NOT EXISTS " +tabelProv+
					" (_id INTEGER PRIMARY KEY NOT NULL," +
					" nama TEXT," +
					" nama_gambar TEXT," +
					" ibukota TEXT," +
					" poin INTEGER);" +
					
					"CREATE TABLE IF NOT EXISTS " +tabelGambar+
					" (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
					" id_prov INTEGER," +
					" nama TEXT," +
					" kategori TEXT," +
					" jawaban TEXT," +
					" FOREIGN KEY(id_prov) REFERENCES " +tabelProv+ "(_id));" +
					
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (1, 'Aceh', 'aceh', 'Banda Aceh', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (2, 'Sumatra Utara', 'sumut', 'Medan', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (3, 'Sumatra Barat', 'sumbar', 'Padang', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (4, 'Riau', 'riau', 'Pekanbaru', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (5, 'Jambi', 'jambi', 'Jambi', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (6, 'Sumatra Selatan', 'sumsel', 'Palembang', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (7, 'Bengkulu', 'bengkulu', 'Bengkulu', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (8, 'Lampung', 'lampung', 'Bandar Lampung', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (9, 'Kepulauan Bangka Belitung', 'kep_babel', 'Pangkal Pinang', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (10, 'Kepulauan Riau', 'kep_riau', 'Tanjung Pinang', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (11, 'Jakarta', 'dki_jakarta', 'Jakarta', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (12, 'Yogyakarta', 'diy', 'Yogyakarta', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (13, 'Jawa Barat', 'jabar', 'Bandung', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (14, 'Jawa Tengah', 'jateng', 'Semarang', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (15, 'Jawa Timur', 'jatim', 'Surabaya', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (16, 'Banten', 'banten', 'Serang', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (17, 'Bali', 'bali', 'Denpasar', 100);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (18, 'Nusa Tenggara Timur', 'ntt', 'Kupang', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (19, 'Nusa Tenggara Barat', 'ntb', 'Mataram', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (20, 'Kalimantan Barat', 'kalbar', 'Pontianak', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (21, 'Kalimantan Tengah', 'kalteng', 'Palangka Raya', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (22, 'Kalimantan Selatan', 'kalsel', 'Banjarmasin', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (23, 'Kalimantan Timur', 'kaltim', 'Samarinda', 0);"+
					//" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota) VALUES (24, 'Kalimantan Utara', 'tim', 'Surabaya');"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (24, 'Sulawesi Utara', 'sulut', 'Manado', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (25, 'Sulawesi Tengah', 'sulteng', 'Palu', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (26, 'Sulawesi Selatan', 'sulsel', 'Makassar', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (27, 'Sulawesi Tenggara', 'sultra', 'Kendari', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (28, 'Sulawesi Barat', 'sulbar', 'Mamuju', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (29, 'Gorontalo', 'gorontalo', 'Gorontalo', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (30, 'Maluku', 'maluku', 'Ambon', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (31, 'Maluku Utara', 'malut', 'Sofifi', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (32, 'Papua', 'papua', 'Jayapura', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (33, 'Papua Barat', 'papbar', 'Manokwari', 0);"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
				
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
				
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Alat Musik Tradisional-Kolintang', 'Alat Musik Tradisional', 'Kolintang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Bandar Udara-Sam Ratulangi', 'Bandar Udara', 'Sam Ratulangi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Fauna-Tangkasi', 'Fauna', 'Tangkasi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Flora-Longusei', 'Flora', 'Longusei');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Makanan Khas Daerah-Lalampa', 'Makanan Khas Daerah', 'Lalampa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Obyek Wisata-Waruga', 'Obyek Wisata', 'Waruga');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Pahlawan-Dr.G.S.S.J.Ratulangi', 'Pahlawan', 'Dr.G.S.S.J.Ratulangi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Pakaian Adat-Minahasa', 'Pakaian Adat', 'Minahasa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Pelabuhan Laut-Manado', 'Pelabuhan Laut', 'Manado');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Perguruan Tinggi-Universitas Sam Ratulangi', 'Perguruan Tinggi', 'Universitas Sam Ratulangi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Rumah Adat-Rumah Bolaang Mongondow', 'Rumah Adat', 'Rumah Bolaang Mongondow');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Senjata Tradisional-Perisai', 'Senjata Tradisional', 'Perisai');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Suku-Sangir', 'Suku', 'Sangir');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Tarian Tradisional-Maengket', 'Tarian Tradisional', 'Maengket');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Alat Musik Tradisional-Ganda', 'Alat Musik Tradisional', 'Ganda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Bandar Udara-Mutiara Palu', 'Bandar Udara', 'Mutiara Palu');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Fauna-Burung Maleo', 'Fauna', 'Burung Maleo');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Flora-Pohon Ebony', 'Flora', 'Pohon Ebony');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Makanan Khas Daerah-Kaledo', 'Makanan Khas Daerah', 'Kaledo');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Obyek Wisata-Bada Napu', 'Obyek Wisata', 'Bada Napu');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Pakaian Adat-Kulavi', 'Pakaian Adat', 'Kulavi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Pelabuhan Laut-Donggala', 'Pelabuhan Laut', 'Donggala');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Perguruan Tinggi-Universitas Tadulako', 'Perguruan Tinggi', 'Universitas Tadulako');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Rumah Adat-Rumah Tambi', 'Rumah Adat', 'Rumah Tambi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Senjata Tradisional-Pasatimpo', 'Senjata Tradisional', 'Pasatimpo');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Suku-Kaili', 'Suku', 'Kaili');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Tarian Tradisional-Dero Poso', 'Tarian Tradisional', 'Dero Poso');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+
					
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');"+

					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Rumah Adat-Rumah Krong Pade', 'Rumah Adat', 'Rumah Krong Pade');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Suku-Aceh', 'Suku', 'Aceh');"+
					" INSERT INTO " +tabelGambar+ "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');";
			
			String[] statements = query.split(";");
			database = context.openOrCreateDatabase(namaDb, Context.MODE_PRIVATE, null);
			for (String statement : statements){
				database.execSQL(statement);
			}
		}
		else 
			database = context.openOrCreateDatabase(namaDb, Context.MODE_PRIVATE, null);
	}
	
	private boolean databaseExist(Context context){
		File dbFile = context.getDatabasePath(namaDb);
		return dbFile.exists();
	}
	
	//--------------------------------------------------OWN FUNCTION----------------------------------------------------------------
	
	public String namaProv(int prov){
		Cursor c = database.rawQuery("SELECT nama FROM " +tabelProv+ " WHERE _id = " +prov, null);
		if(c.moveToFirst()){
			return c.getString(c.getColumnIndex("nama"));
		}
		return "";
	}
	
	public String namaIbukota(int prov){
		Cursor c = database.rawQuery("SELECT ibukota FROM " +tabelProv+ " WHERE _id = " +prov, null);
		if(c.moveToFirst()){
			return c.getString(c.getColumnIndex("ibukota"));
		}
		return "";
	}
	
	public Cursor getProv(){
		return database.rawQuery("SELECT nama_gambar AS gambar FROM " +tabelProv+ " ORDER BY _id" , null);
	}
	
	public int jumlahGambarProv(int prov){
		Cursor c = database.rawQuery("SELECT COUNT(*) FROM " +tabelGambar+ " WHERE id_prov = " +prov , null);
		if(c.moveToFirst()){
			return c.getInt(c.getColumnIndex("COUNT(*)"));
		}
		return 0;
	}
	
	public int maxPoin(int prov){
		Cursor c = database.rawQuery("SELECT poin FROM " +tabelProv+ " WHERE _id = " +prov , null);
		if(c.moveToFirst()){
			return c.getInt(c.getColumnIndex("poin"));
		}
		return 0;
	}
	
	//--------------------------------------------------OWN FUNCTION----------------------------------------------------------------
	
	public void close(){
		if(database != null)
			database.close();
	}
	
	

}
