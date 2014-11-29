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
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (9, 'Kepulauan Bangka Belitung', 'kepbabel', 'Pangkal Pinang', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (10, 'Kepulauan Riau', 'kepriau', 'Tanjung Pinang', 0);"+
					" INSERT INTO " +tabelProv+ "(_id, nama, nama_gambar, ibukota, poin) VALUES (11, 'Jakarta', 'dki', 'Jakarta', 0);"+
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
					
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Alat Musik Tradisional-Canang', 'Alat Musik Tradisional', 'Canang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Bandar Udara-Sultan Iskandar Muda', 'Bandar Udara', 'Sultan Iskandar Muda');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Fauna-Cempala Kuneng', 'Fauna', 'Cempala Kuneng');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Flora-Bungong Jeumpa', 'Flora', 'Bungong Jeumpa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Makanan Khas Daerah-Kanji Rumbi', 'Makanan Khas Daerah', 'Kanji Rumbi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Obyek Wisata-Masjid Raya Baiturrahman', 'Obyek Wisata', 'Masjid Raya Baiturrahman');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Pahlawan-Cut Nyak Dhien', 'Pahlawan', 'Cut Nyak Dhien');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Pakaian Adat-Pidie', 'Pakaian Adat', 'Pidie');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Pelabuhan Laut-Balohan', 'Pelabuhan Laut', 'Balohan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Perguruan Tinggi-Universitas Iskandar Muda', 'Perguruan Tinggi', 'Universitas Iskandar Muda');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Rumah Adat-Krong Pade', 'Rumah Adat', 'Krong Pade');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Senjata Tradisional-Rencong', 'Senjata Tradisional', 'Rencong');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Suku-Aceh', 'Suku', 'Aceh');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (1, 'Tarian Tradisional-Saman', 'Tarian Tradisional', 'Saman');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Alat Musik Tradisional-Aramba', 'Alat Musik Tradisional', 'Aramba');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Bandar Udara-Kuala Namu', 'Bandar Udara', 'Kuala Namu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Fauna-Beo Nias', 'Fauna', 'Beo Nias');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Flora-Bunga Kenanga', 'Flora', 'Bunga Kenanga');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Makanan Khas Daerah-Bika Ambon', 'Makanan Khas Daerah', 'Bika Ambon');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Obyek Wisata-Danau Toba', 'Obyek Wisata', 'Danau Toba');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Pahlawan- Si Singamangaraja XII', 'Pahlawan', ' Si Singamangaraja XII');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Pakaian Adat-Karo', 'Pakaian Adat', 'Karo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Pelabuhan Laut-Belawan', 'Pelabuhan Laut', 'Belawan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Perguruan Tinggi-Universitas Negeri Medan', 'Perguruan Tinggi', 'Universitas Negeri Medan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Rumah Adat-Bolon', 'Rumah Adat', 'Bolon');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Senjata Tradisional-Piso Podang', 'Senjata Tradisional', 'Piso Podang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Suku-Batak', 'Suku', 'Batak');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (2, 'Tarian Tradisional-Tor Tor', 'Tarian Tradisional', 'Tor Tor');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Alat Musik Tradisional-Saluang', 'Alat Musik Tradisional', 'Saluang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Bandar Udara-Tabing', 'Bandar Udara', 'Tabing');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Fauna-Kuau Besar', 'Fauna', 'Kuau Besar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Flora-Pohon Andalas', 'Flora', 'Pohon Andalas');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Makanan Khas Daerah-Rendang', 'Makanan Khas Daerah', 'Rendang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Obyek Wisata-Jam Gadang', 'Obyek Wisata', 'Jam Gadang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Pahlawan-Teuku Imam Bonjol', 'Pahlawan', 'Teuku Imam Bonjol');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Pakaian Adat-Batu Sangkar', 'Pakaian Adat', 'Batu Sangkar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Pelabuhan Laut-Teluk Bayur', 'Pelabuhan Laut', 'Teluk Bayur');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Perguruan Tinggi-Universitas Andalas', 'Perguruan Tinggi', 'Universitas Andalas');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Rumah Adat-Rumah Gadang', 'Rumah Adat', 'Rumah Gadang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Senjata Tradisional-Piarit', 'Senjata Tradisional', 'Piarit');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Suku-Mentawai', 'Suku', 'Mentawai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (3, 'Tarian Tradisional-Lilin', 'Tarian Tradisional', 'Lilin');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Alat Musik Tradisional-Rebana', 'Alat Musik Tradisional', 'Rebana');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Bandar Udara-Sultan Syarif Kasim II', 'Bandar Udara', 'Sultan Syarif Kasim II');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Fauna-Srindit', 'Fauna', 'Srindit');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Flora-Nibung', 'Flora', 'Nibung');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Obyek Wisata-Candi Muara Takus', 'Obyek Wisata', 'Candi Muara Takus');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Pahlawan-Sultan Syarif Kasim II', 'Pahlawan', 'Sultan Syarif Kasim II');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Pakaian Adat-Teluk Belanga', 'Pakaian Adat', 'Teluk Belanga');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Pelabuhan Laut-Bengkalis', 'Pelabuhan Laut', 'Bengkalis');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Perguruan Tinggi-Universitas Riau', 'Perguruan Tinggi', 'Universitas Riau');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Rumah Adat-Melayu Selaso Jatuh Kembar', 'Rumah Adat', 'Melayu Selaso Jatuh Kembar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Senjata Tradisional-Pedang Jenawi', 'Senjata Tradisional', 'Pedang Jenawi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Suku-Sakai', 'Suku', 'Sakai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (4, 'Tarian Tradisional-Zapin', 'Tarian Tradisional', 'Zapin');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Alat Musik Tradisional-Gambus', 'Alat Musik Tradisional', 'Gambus');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Bandar Udara-Sultan Tahaha Syarifuddin', 'Bandar Udara', 'Sultan Tahaha Syarifuddin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Fauna-Harimau Sumatera', 'Fauna', 'Harimau Sumatera');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Flora-Pinang Merah', 'Flora', 'Pinang Merah');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Makanan Khas Daerah-Tempoyak', 'Makanan Khas Daerah', 'Tempoyak');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Obyek Wisata-Taman Nasional Kerinci', 'Obyek Wisata', 'Taman Nasional Kerinci');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Pahlawan-Sultan Tahaha Syarifuddin', 'Pahlawan', 'Sultan Tahaha Syarifuddin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Pakaian Adat-Jambi', 'Pakaian Adat', 'Jambi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Pelabuhan Laut-Kuala Tungkal', 'Pelabuhan Laut', 'Kuala Tungkal');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Perguruan Tinggi-Universitas Negeri Jambi', 'Perguruan Tinggi', 'Universitas Negeri Jambi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Rumah Adat-Panjang', 'Rumah Adat', 'Panjang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Senjata Tradisional-Badik Tumbuk Lada', 'Senjata Tradisional', 'Badik Tumbuk Lada');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Suku-Kerinci', 'Suku', 'Kerinci');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (5, 'Tarian Tradisional-Tari Kipas Keprak', 'Tarian Tradisional', 'Tari Kipas Keprak');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Alat Musik Tradisional-Accordion', 'Alat Musik Tradisional', 'Accordion');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Bandar Udara-Sultan Mahmud Badaruddin II', 'Bandar Udara', 'Sultan Mahmud Badaruddin II');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Fauna-Belida', 'Fauna', 'Belida');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Flora-Duku', 'Flora', 'Duku');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Makanan Khas Daerah-Pempek Palembang', 'Makanan Khas Daerah', 'Pempek Palembang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Obyek Wisata-Danau Ranau', 'Obyek Wisata', 'Danau Ranau');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Pahlawan-Sultan Mahmud Badaruddin II', 'Pahlawan', 'Sultan Mahmud Badaruddin II');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Pakaian Adat-Aisan Gede', 'Pakaian Adat', 'Aisan Gede');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Pelabuhan Laut-Palembang', 'Pelabuhan Laut', 'Palembang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Perguruan Tinggi-Universitas Sriwijaya', 'Perguruan Tinggi', 'Universitas Sriwijaya');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Rumah Adat-Limas', 'Rumah Adat', 'Limas');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Senjata Tradisional-Siwar', 'Senjata Tradisional', 'Siwar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Suku-Kubu', 'Suku', 'Kubu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (6, 'Tarian Tradisional-Gending Sriwijaya', 'Tarian Tradisional', 'Gending Sriwijaya');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Alat Musik Tradisional-Doll', 'Alat Musik Tradisional', 'Doll');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Bandar Udara-Padang Kemiling', 'Bandar Udara', 'Padang Kemiling');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Fauna-Beruang Madu', 'Fauna', 'Beruang Madu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Flora-Bunga Bangkai', 'Flora', 'Bunga Bangkai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Makanan Khas Daerah- Oncong Oncong Pisang', 'Makanan Khas Daerah', ' Oncong Oncong Pisang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Obyek Wisata-Benteng Marlborough', 'Obyek Wisata', 'Benteng Marlborough');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Pahlawan-Fatmawati Soekarno', 'Pahlawan', 'Fatmawati Soekarno');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Pakaian Adat-Bengkulu', 'Pakaian Adat', 'Bengkulu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Pelabuhan Laut-Pulau Baai', 'Pelabuhan Laut', 'Pulau Baai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Perguruan Tinggi-Universitas Bengkulu', 'Perguruan Tinggi', 'Universitas Bengkulu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Rumah Adat-Rakyat', 'Rumah Adat', 'Rakyat');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Senjata Tradisional-Keris', 'Senjata Tradisional', 'Keris');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (7, 'Tarian Tradisional-Bidadari', 'Tarian Tradisional', 'Bidadari');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Alat Musik Tradisional-Bende', 'Alat Musik Tradisional', 'Bende');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Bandar Udara-Radin Inten', 'Bandar Udara', 'Radin Inten');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Fauna-Gajah Lampung', 'Fauna', 'Gajah Lampung');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Flora-Kembang Ashar', 'Flora', 'Kembang Ashar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Makanan Khas Daerah-Seruit Lampung', 'Makanan Khas Daerah', 'Seruit Lampung');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Obyek Wisata-Way Kambas', 'Obyek Wisata', 'Way Kambas');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Pahlawan-Radin Inten II', 'Pahlawan', 'Radin Inten II');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Pakaian Adat-Tulang Bawang', 'Pakaian Adat', 'Tulang Bawang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Pelabuhan Laut-Bakauheni', 'Pelabuhan Laut', 'Bakauheni');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Perguruan Tinggi-Universitas Lampung', 'Perguruan Tinggi', 'Universitas Lampung');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Rumah Adat-Nuwou Sesat', 'Rumah Adat', 'Nuwou Sesat');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Senjata Tradisional-Payan', 'Senjata Tradisional', 'Payan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Suku-Saibatin', 'Suku', 'Saibatin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (8, 'Tarian Tradisional-Jangget', 'Tarian Tradisional', 'Jangget');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Alat Musik Tradisional-Dambus', 'Alat Musik Tradisional', 'Dambus');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Bandar Udara-HAS Hanandjoedin', 'Bandar Udara', 'HAS Hanandjoedin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Flora-Nagasari', 'Flora', 'Nagasari');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Makanan Khas Daerah-Calok', 'Makanan Khas Daerah', 'Calok');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Obyek Wisata-Gunung Maras', 'Obyek Wisata', 'Gunung Maras');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Pakaian Adat-Aisan Gede', 'Pakaian Adat', 'Aisan Gede');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Pelabuhan Laut-Muntok', 'Pelabuhan Laut', 'Muntok');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Rumah Adat-Rakit', 'Rumah Adat', 'Rakit');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Senjata Tradisional-Kedik', 'Senjata Tradisional', 'Kedik');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Suku-Melayu', 'Suku', 'Melayu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (9, 'Tarian Tradisional-Pucuk Purun', 'Tarian Tradisional', 'Pucuk Purun');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Alat Musik Tradisional-Gendang Panjang', 'Alat Musik Tradisional', 'Gendang Panjang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Bandar Udara-Hang Nadim', 'Bandar Udara', 'Hang Nadim');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Fauna-Harimau Sumatera', 'Fauna', 'Harimau Sumatera');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Flora-Pinang Merah', 'Flora', 'Pinang Merah');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Obyek Wisata-Candi Muara Takus', 'Obyek Wisata', 'Candi Muara Takus');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Pahlawan-Tuanku Tambusai', 'Pahlawan', 'Tuanku Tambusai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Pakaian Adat-Teluk Belanga', 'Pakaian Adat', 'Teluk Belanga');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Pelabuhan Laut-Batam', 'Pelabuhan Laut', 'Batam');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Rumah Adat-Melayu Selaso Jatuh Kembar', 'Rumah Adat', 'Melayu Selaso Jatuh Kembar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Senjata Tradisional-Pedang Jenawi', 'Senjata Tradisional', 'Pedang Jenawi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Suku-Kerinci', 'Suku', 'Kerinci');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (10, 'Tarian Tradisional-Tabal Gempita', 'Tarian Tradisional', 'Tabal Gempita');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Alat Musik Tradisional-Tehyan', 'Alat Musik Tradisional', 'Tehyan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Bandar Udara-Halim PerdanaKusumah', 'Bandar Udara', 'Halim PerdanaKusumah');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Fauna-Elang Bondol', 'Fauna', 'Elang Bondol');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Flora-Salak Condet', 'Flora', 'Salak Condet');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Makanan Khas Daerah-Kerak Telor', 'Makanan Khas Daerah', 'Kerak Telor');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Obyek Wisata-TMII', 'Obyek Wisata', 'TMII');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Pahlawan-M.Husni Thamrin', 'Pahlawan', 'M.Husni Thamrin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Pakaian Adat-Abang None', 'Pakaian Adat', 'Abang None');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Pelabuhan Laut-Tanjung Priok', 'Pelabuhan Laut', 'Tanjung Priok');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Perguruan Tinggi-Universitas Indonesia', 'Perguruan Tinggi', 'Universitas Indonesia');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Rumah Adat-Kebaya', 'Rumah Adat', 'Kebaya');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Senjata Tradisional-Golok', 'Senjata Tradisional', 'Golok');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Suku-Betawi', 'Suku', 'Betawi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (11, 'Tarian Tradisional-Ronggeng', 'Tarian Tradisional', 'Ronggeng');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Alat Musik Tradisional-Gendang', 'Alat Musik Tradisional', 'Gendang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Bandar Udara-Adi Sucipto', 'Bandar Udara', 'Adi Sucipto');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Fauna-Burung Perkutut', 'Fauna', 'Burung Perkutut');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Flora-Kepel', 'Flora', 'Kepel');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Makanan Khas Daerah-Gudeg Jogja', 'Makanan Khas Daerah', 'Gudeg Jogja');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Obyek Wisata-Pantai Parang Tritis', 'Obyek Wisata', 'Pantai Parang Tritis');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Pahlawan-Pangeran Diponegoro', 'Pahlawan', 'Pangeran Diponegoro');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Pakaian Adat-Jogjakarta', 'Pakaian Adat', 'Jogjakarta');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Perguruan Tinggi-Universitas Gajah Mada', 'Perguruan Tinggi', 'Universitas Gajah Mada');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Rumah Adat-Joglo', 'Rumah Adat', 'Joglo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Senjata Tradisional-Keris', 'Senjata Tradisional', 'Keris');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Suku-Jawa', 'Suku', 'Jawa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (12, 'Tarian Tradisional-Bedaya', 'Tarian Tradisional', 'Bedaya');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Alat Musik Tradisional-Angklung', 'Alat Musik Tradisional', 'Angklung');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Bandar Udara-Husein Sastranegara', 'Bandar Udara', 'Husein Sastranegara');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Fauna-Macan Tutul', 'Fauna', 'Macan Tutul');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Flora-Gandaria', 'Flora', 'Gandaria');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Makanan Khas Daerah-Oncom', 'Makanan Khas Daerah', 'Oncom');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Obyek Wisata-Kebun Raya Bogor', 'Obyek Wisata', 'Kebun Raya Bogor');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Pahlawan-Otto Iskandardinata', 'Pahlawan', 'Otto Iskandardinata');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Pakaian Adat-Jawa Barat', 'Pakaian Adat', 'Jawa Barat');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Pelabuhan Laut-Cirebon', 'Pelabuhan Laut', 'Cirebon');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Perguruan Tinggi-Universitas Padjajaran', 'Perguruan Tinggi', 'Universitas Padjajaran');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Rumah Adat-Kasepuhan Cirebon', 'Rumah Adat', 'Kasepuhan Cirebon');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Senjata Tradisional-Kujang', 'Senjata Tradisional', 'Kujang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Suku-Sunda', 'Suku', 'Sunda');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (13, 'Tarian Tradisional-Merak', 'Tarian Tradisional', 'Merak');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Alat Musik Tradisional-Gamelan', 'Alat Musik Tradisional', 'Gamelan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Bandar Udara-Ahmad Yani', 'Bandar Udara', 'Ahmad Yani');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Fauna-Burung Kepodang', 'Fauna', 'Burung Kepodang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Flora-Bunga Kantil', 'Flora', 'Bunga Kantil');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Makanan Khas Daerah-Lumpia', 'Makanan Khas Daerah', 'Lumpia');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Obyek Wisata-Gunung Dieng', 'Obyek Wisata', 'Gunung Dieng');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Pahlawan-Nyi Ageng Serang', 'Pahlawan', 'Nyi Ageng Serang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Pakaian Adat-Jawa', 'Pakaian Adat', 'Jawa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Pelabuhan Laut-Tanjung Mas', 'Pelabuhan Laut', 'Tanjung Mas');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Perguruan Tinggi-Universitas Diponegoro', 'Perguruan Tinggi', 'Universitas Diponegoro');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Senjata Tradisional-Keris', 'Senjata Tradisional', 'Keris');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Suku-Jawa', 'Suku', 'Jawa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (14, 'Tarian Tradisional-Bambangan Cakil', 'Tarian Tradisional', 'Bambangan Cakil');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Alat Musik Tradisional-Bonang', 'Alat Musik Tradisional', 'Bonang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Bandar Udara-Juanda', 'Bandar Udara', 'Juanda');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Fauna-Ayam Bekisar', 'Fauna', 'Ayam Bekisar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Flora-Bunga Sedap Malam', 'Flora', 'Bunga Sedap Malam');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Makanan Khas Daerah-Rujak Cingur', 'Makanan Khas Daerah', 'Rujak Cingur');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Obyek Wisata-Candi Kidal', 'Obyek Wisata', 'Candi Kidal');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Pahlawan-KH. Wahid Hasyim', 'Pahlawan', 'KH. Wahid Hasyim');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Pakaian Adat-Madura', 'Pakaian Adat', 'Madura');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Pelabuhan Laut-Tanjung Perak', 'Pelabuhan Laut', 'Tanjung Perak');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Perguruan Tinggi-Institut Teknologi Sepuluh Nopember', 'Perguruan Tinggi', 'Institut Teknologi Sepuluh Nopember');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Rumah Adat-Situbondo', 'Rumah Adat', 'Situbondo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Senjata Tradisional-Clurit', 'Senjata Tradisional', 'Clurit');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Suku-Tengger', 'Suku', 'Tengger');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (15, 'Tarian Tradisional-Remong', 'Tarian Tradisional', 'Remong');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Alat Musik Tradisional-Gendang', 'Alat Musik Tradisional', 'Gendang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Bandar Udara-Soekarno Hatta', 'Bandar Udara', 'Soekarno Hatta');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Fauna-Badak Jawa', 'Fauna', 'Badak Jawa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Flora-Kokoleceran', 'Flora', 'Kokoleceran');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Makanan Khas Daerah-Angeun Lada', 'Makanan Khas Daerah', 'Angeun Lada');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Obyek Wisata-Taman Nasional Ujung Kulon', 'Obyek Wisata', 'Taman Nasional Ujung Kulon');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Pahlawan-Sultan Ageng Tirtayasa', 'Pahlawan', 'Sultan Ageng Tirtayasa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Pakaian Adat-Pengantin', 'Pakaian Adat', 'Pengantin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Pelabuhan Laut-Merak', 'Pelabuhan Laut', 'Merak');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Perguruan Tinggi-Universitas Sultan Ageng Tirtayasa', 'Perguruan Tinggi', 'Universitas Sultan Ageng Tirtayasa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Rumah Adat-Badui', 'Rumah Adat', 'Badui');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Senjata Tradisional-Kujang', 'Senjata Tradisional', 'Kujang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Suku-Baduy', 'Suku', 'Baduy');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (16, 'Tarian Tradisional-Topeng', 'Tarian Tradisional', 'Topeng');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Alat Musik Tradisional-Cengceng', 'Alat Musik Tradisional', 'Cengceng');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Bandar Udara-Ngurah Rai', 'Bandar Udara', 'Ngurah Rai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Fauna-Jalak Bali', 'Fauna', 'Jalak Bali');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Flora-Majegau', 'Flora', 'Majegau');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Makanan Khas Daerah-Ayam Betutu', 'Makanan Khas Daerah', 'Ayam Betutu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Obyek Wisata-Pantai Kuta', 'Obyek Wisata', 'Pantai Kuta');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Pahlawan-I Gusti Ngurah Rai', 'Pahlawan', 'I Gusti Ngurah Rai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Pakaian Adat-Payas Agung', 'Pakaian Adat', 'Payas Agung');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Pelabuhan Laut-Gilimanuk', 'Pelabuhan Laut', 'Gilimanuk');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Perguruan Tinggi-Universitas Udayana', 'Perguruan Tinggi', 'Universitas Udayana');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Rumah Adat-Gapura Candi Bentar', 'Rumah Adat', 'Gapura Candi Bentar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Senjata Tradisional-Keris', 'Senjata Tradisional', 'Keris');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Tarian Tradisional-Bali Aga', 'Tarian Tradisional', 'Bali Aga');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (17, 'Tarian Tradisional-Legong', 'Tarian Tradisional', 'Legong');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Alat Musik Tradisional-Sasando', 'Alat Musik Tradisional', 'Sasando');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Bandar Udara-El Tari', 'Bandar Udara', 'El Tari');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Fauna-Komodo', 'Fauna', 'Komodo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Flora-Kayu Cendana', 'Flora', 'Kayu Cendana');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Makanan Khas Daerah-Petepah Manuk', 'Makanan Khas Daerah', 'Petepah Manuk');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Obyek Wisata-Danau Tiga warna', 'Obyek Wisata', 'Danau Tiga warna');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Pahlawan-Izaak Huru doko', 'Pahlawan', 'Izaak Huru doko');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Pakaian Adat-Nusa Tenggara Timur', 'Pakaian Adat', 'Nusa Tenggara Timur');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Pelabuhan Laut-Tenau', 'Pelabuhan Laut', 'Tenau');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Perguruan Tinggi-Universitas Nusa Cendana', 'Perguruan Tinggi', 'Universitas Nusa Cendana');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Rumah Adat-Musalaki', 'Rumah Adat', 'Musalaki');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Senjata Tradisional-Sundu', 'Senjata Tradisional', 'Sundu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Suku-Rote', 'Suku', 'Rote');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (18, 'Tarian Tradisional-Perang', 'Tarian Tradisional', 'Perang');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Alat Musik Tradisional-Serunai', 'Alat Musik Tradisional', 'Serunai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Bandar Udara-Lombok', 'Bandar Udara', 'Lombok');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Fauna-Rusa Timur', 'Fauna', 'Rusa Timur');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Flora-Ajan Kelicung', 'Flora', 'Ajan Kelicung');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Makanan Khas Daerah-Gecak Sape', 'Makanan Khas Daerah', 'Gecak Sape');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Obyek Wisata-Pura Meru', 'Obyek Wisata', 'Pura Meru');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Pakaian Adat-Sumbawa', 'Pakaian Adat', 'Sumbawa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Pelabuhan Laut-Bangsal', 'Pelabuhan Laut', 'Bangsal');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Perguruan Tinggi-Universitas Mataram', 'Perguruan Tinggi', 'Universitas Mataram');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Rumah Adat-Istana Sultan Sumbawa', 'Rumah Adat', 'Istana Sultan Sumbawa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Senjata Tradisional-Keris', 'Senjata Tradisional', 'Keris');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Suku-Sasak', 'Suku', 'Sasak');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (19, 'Tarian Tradisional-Mpaa Lenggogo', 'Tarian Tradisional', 'Mpaa Lenggogo');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Alat Musik Tradisional-Tuma', 'Alat Musik Tradisional', 'Tuma');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Bandar Udara-Supadio', 'Bandar Udara', 'Supadio');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Fauna-Enggang Gading', 'Fauna', 'Enggang Gading');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Flora-Tengkawang', 'Flora', 'Tengkawang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Makanan Khas Daerah-Bubur Pedas', 'Makanan Khas Daerah', 'Bubur Pedas');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Obyek Wisata-Keraton Sambas', 'Obyek Wisata', 'Keraton Sambas');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Pahlawan-Abdul Kadir Raden Temenggung Setia Pahlawan', 'Pahlawan', 'Abdul Kadir Raden Temenggung Setia Pahlawan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Pakaian Adat-Perang', 'Pakaian Adat', 'Perang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Pelabuhan Laut-Pontianak', 'Pelabuhan Laut', 'Pontianak');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Perguruan Tinggi-Universitas Tanjung Pura', 'Perguruan Tinggi', 'Universitas Tanjung Pura');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Rumah Adat-Istana Kesultanan Pontianak', 'Rumah Adat', 'Istana Kesultanan Pontianak');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Senjata Tradisional-Mandau', 'Senjata Tradisional', 'Mandau');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Suku-Kayau', 'Suku', 'Kayau');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (20, 'Tarian Tradisional-Monong', 'Tarian Tradisional', 'Monong');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Alat Musik Tradisional-Japen', 'Alat Musik Tradisional', 'Japen');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Bandar Udara-Tjilik Riwut', 'Bandar Udara', 'Tjilik Riwut');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Fauna-Kuau Melayu', 'Fauna', 'Kuau Melayu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Flora-Tenggaring', 'Flora', 'Tenggaring');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Makanan Khas Daerah-Kalo Kalo', 'Makanan Khas Daerah', 'Kalo Kalo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Obyek Wisata-Taman Nasional Tanjung Puting', 'Obyek Wisata', 'Taman Nasional Tanjung Puting');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Pahlawan-Tjilik Riwut', 'Pahlawan', 'Tjilik Riwut');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Pakaian Adat-Sinjang', 'Pakaian Adat', 'Sinjang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Pelabuhan Laut-Sampit', 'Pelabuhan Laut', 'Sampit');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Perguruan Tinggi-Universitas Palangkaraya', 'Perguruan Tinggi', 'Universitas Palangkaraya');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Rumah Adat-Betang', 'Rumah Adat', 'Betang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Senjata Tradisional-Talawang', 'Senjata Tradisional', 'Talawang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Suku-Dayak', 'Suku', 'Dayak');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (21, 'Tarian Tradisional-Balean Dadas', 'Tarian Tradisional', 'Balean Dadas');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Alat Musik Tradisional-Panting', 'Alat Musik Tradisional', 'Panting');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Bandar Udara-Syamsudin Noor', 'Bandar Udara', 'Syamsudin Noor');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Fauna-Bekantan', 'Fauna', 'Bekantan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Flora-Mangifera Casturi', 'Flora', 'Mangifera Casturi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Makanan Khas Daerah-Ayam masak hijau', 'Makanan Khas Daerah', 'Ayam masak hijau');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Obyek Wisata-Masjid Sultan Sriansyah', 'Obyek Wisata', 'Masjid Sultan Sriansyah');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Pahlawan-Pangeran Antasari', 'Pahlawan', 'Pangeran Antasari');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Pakaian Adat-Banjar', 'Pakaian Adat', 'Banjar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Pelabuhan Laut-Trisakti', 'Pelabuhan Laut', 'Trisakti');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Perguruan Tinggi-Universitas Lambung Mangkurat', 'Perguruan Tinggi', 'Universitas Lambung Mangkurat');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Rumah Adat-Banjar Bubungan Tinggi', 'Rumah Adat', 'Banjar Bubungan Tinggi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Senjata Tradisional-Keris', 'Senjata Tradisional', 'Keris');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Suku-Banjar', 'Suku', 'Banjar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (22, 'Tarian Tradisional-Baksa Kembang', 'Tarian Tradisional', 'Baksa Kembang');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Alat Musik Tradisional-Sampe', 'Alat Musik Tradisional', 'Sampe');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Bandar Udara-Temindung', 'Bandar Udara', 'Temindung');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Fauna-Pesut Mahakam', 'Fauna', 'Pesut Mahakam');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Flora-Anggrek Hitam', 'Flora', 'Anggrek Hitam');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Makanan Khas Daerah-Sanga Cobek Tigu', 'Makanan Khas Daerah', 'Sanga Cobek Tigu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Obyek Wisata-Keraton Kerajaan Kutai Kertanegara', 'Obyek Wisata', 'Keraton Kerajaan Kutai Kertanegara');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Pakaian Adat-Urang Besunung', 'Pakaian Adat', 'Urang Besunung');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Pelabuhan Laut-Samarinda', 'Pelabuhan Laut', 'Samarinda');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Perguruan Tinggi-Universitas Mulawarman', 'Perguruan Tinggi', 'Universitas Mulawarman');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Rumah Adat-Lamin', 'Rumah Adat', 'Lamin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Senjata Tradisional-Mandau', 'Senjata Tradisional', 'Mandau');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Suku-Ngaju', 'Suku', 'Ngaju');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (23, 'Tarian Tradisional-Gong', 'Tarian Tradisional', 'Gong');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Alat Musik Tradisional-Kolintang', 'Alat Musik Tradisional', 'Kolintang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Bandar Udara-Sam Ratulangi', 'Bandar Udara', 'Sam Ratulangi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Fauna-Tangkasi', 'Fauna', 'Tangkasi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Flora-Longusei', 'Flora', 'Longusei');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Makanan Khas Daerah-Bubur Manado', 'Makanan Khas Daerah', 'Bubur Manado');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Obyek Wisata-Bunaken', 'Obyek Wisata', 'Bunaken');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Pahlawan-Dr.G.S.S.J.Ratulangi', 'Pahlawan', 'Dr.G.S.S.J.Ratulangi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Pakaian Adat-Minahasa', 'Pakaian Adat', 'Minahasa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Pelabuhan Laut-Manado', 'Pelabuhan Laut', 'Manado');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Perguruan Tinggi-Universitas Sam Ratulangi', 'Perguruan Tinggi', 'Universitas Sam Ratulangi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Rumah Adat-Bolaang Mongondow', 'Rumah Adat', 'Bolaang Mongondow');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Senjata Tradisional-Sabel', 'Senjata Tradisional', 'Sabel');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Suku-Halmahera', 'Suku', 'Halmahera');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (24, 'Tarian Tradisional-Cakalele', 'Tarian Tradisional', 'Cakalele');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Alat Musik Tradisional-Ganda', 'Alat Musik Tradisional', 'Ganda');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Bandar Udara-Mutiara Palu', 'Bandar Udara', 'Mutiara Palu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Fauna-Burung Maleo', 'Fauna', 'Burung Maleo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Flora-Pohon Ebony', 'Flora', 'Pohon Ebony');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Makanan Khas Daerah-Kaledo', 'Makanan Khas Daerah', 'Kaledo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Obyek Wisata-Air Terjun Wera', 'Obyek Wisata', 'Air Terjun Wera');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Pakaian Adat-Kulavi', 'Pakaian Adat', 'Kulavi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Pelabuhan Laut-Donggala', 'Pelabuhan Laut', 'Donggala');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Perguruan Tinggi-Universitas Tadulako', 'Perguruan Tinggi', 'Universitas Tadulako');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Rumah Adat-Tambi', 'Rumah Adat', 'Tambi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Senjata Tradisional-Parang', 'Senjata Tradisional', 'Parang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Suku-Toli Toli', 'Suku', 'Toli Toli');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (25, 'Tarian Tradisional-Lumense', 'Tarian Tradisional', 'Lumense');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Alat Musik Tradisional-Talindo', 'Alat Musik Tradisional', 'Talindo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Bandar Udara-Hasanuddin', 'Bandar Udara', 'Hasanuddin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Fauna-Kerbau Belang', 'Fauna', 'Kerbau Belang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Flora-Pohon Lontar', 'Flora', 'Pohon Lontar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Makanan Khas Daerah-Coto Makassar', 'Makanan Khas Daerah', 'Coto Makassar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Obyek Wisata-Benteng Ujung Pandang', 'Obyek Wisata', 'Benteng Ujung Pandang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Pahlawan-Sultan Hasanuddin', 'Pahlawan', 'Sultan Hasanuddin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Pakaian Adat-Toraja', 'Pakaian Adat', 'Toraja');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Pelabuhan Laut-Ujung Pandang', 'Pelabuhan Laut', 'Ujung Pandang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Perguruan Tinggi-Universitas Hasanuddin', 'Perguruan Tinggi', 'Universitas Hasanuddin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Rumah Adat-Tongkonan', 'Rumah Adat', 'Tongkonan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Senjata Tradisional-Sabele', 'Senjata Tradisional', 'Sabele');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Suku-Makassar', 'Suku', 'Makassar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (26, 'Tarian Tradisional-Pakarena', 'Tarian Tradisional', 'Pakarena');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Alat Musik Tradisional-Lado Lado', 'Alat Musik Tradisional', 'Lado Lado');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Bandar Udara-Monginsidi', 'Bandar Udara', 'Monginsidi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Fauna-Anoa', 'Fauna', 'Anoa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Flora-Anggrek Serat', 'Flora', 'Anggrek Serat');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Makanan Khas Daerah-Sasate Nangka', 'Makanan Khas Daerah', 'Sasate Nangka');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Obyek Wisata-Air Terjun Moramo', 'Obyek Wisata', 'Air Terjun Moramo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Pakaian Adat-Babung Ginasamani', 'Pakaian Adat', 'Babung Ginasamani');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Pelabuhan Laut-Kendari', 'Pelabuhan Laut', 'Kendari');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Perguruan Tinggi-Universitas Haluoleo', 'Perguruan Tinggi', 'Universitas Haluoleo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Rumah Adat-Istana Buton', 'Rumah Adat', 'Istana Buton');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Senjata Tradisional-Sumpitan', 'Senjata Tradisional', 'Sumpitan');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Suku-Tolai', 'Suku', 'Tolai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (27, 'Tarian Tradisional-Balumpa', 'Tarian Tradisional', 'Balumpa');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Alat Musik Tradisional-Kecapi', 'Alat Musik Tradisional', 'Kecapi');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Fauna-Kerbau Belang', 'Fauna', 'Kerbau Belang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Flora-Pohon Lontar', 'Flora', 'Pohon Lontar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Obyek Wisata-Pantai Palippis', 'Obyek Wisata', 'Pantai Palippis');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Obyek Wisata-Situs Makam Raja Todilaling', 'Obyek Wisata', 'Situs Makam Raja Todilaling');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Pakaian Adat-Sulawesi Barat', 'Pakaian Adat', 'Sulawesi Barat');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Rumah Adat-Rumah Mandar', 'Rumah Adat', 'Rumah Mandar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Suku-Bugis', 'Suku', 'Bugis');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Tarian Tradisional-Kipas', 'Tarian Tradisional', 'Kipas');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (28, 'Tarian Tradisional-Kondo Sapata', 'Tarian Tradisional', 'Kondo Sapata');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Alat Musik Tradisional-Ganda', 'Alat Musik Tradisional', 'Ganda');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Bandar Udara-Jalaludin', 'Bandar Udara', 'Jalaludin');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Flora-Gofasa', 'Flora', 'Gofasa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Makanan Khas Daerah-Ilabulo', 'Makanan Khas Daerah', 'Ilabulo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Obyek Wisata-Benteng Otanaha', 'Obyek Wisata', 'Benteng Otanaha');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Pahlawan-Nani Wartabone', 'Pahlawan', 'Nani Wartabone');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Pakaian Adat-Biliu', 'Pakaian Adat', 'Biliu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Pelabuhan Laut-Gorontalo', 'Pelabuhan Laut', 'Gorontalo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Perguruan Tinggi-Universitas Negeri Gorontalo', 'Perguruan Tinggi', 'Universitas Negeri Gorontalo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Rumah Adat-Pewaris', 'Rumah Adat', 'Pewaris');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Senjata Tradisional-Badik', 'Senjata Tradisional', 'Badik');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Suku-Gorontalo', 'Suku', 'Gorontalo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (29, 'Tarian Tradisional-Paduppa', 'Tarian Tradisional', 'Paduppa');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Alat Musik Tradisional-Nafiri', 'Alat Musik Tradisional', 'Nafiri');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Bandar Udara-Pattimura', 'Bandar Udara', 'Pattimura');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Fauna-Nuri Raja', 'Fauna', 'Nuri Raja');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Flora-Anggrek Larat', 'Flora', 'Anggrek Larat');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Makanan Khas Daerah-Palai Badar', 'Makanan Khas Daerah', 'Palai Badar');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Obyek Wisata-Tugu Martha Christina Tiahahu', 'Obyek Wisata', 'Tugu Martha Christina Tiahahu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Pahlawan-Kapitan Pattimura', 'Pahlawan', 'Kapitan Pattimura');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Pakaian Adat-Maluku', 'Pakaian Adat', 'Maluku');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Pelabuhan Laut-Ambon', 'Pelabuhan Laut', 'Ambon');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Rumah Adat-Baileo', 'Rumah Adat', 'Baileo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Senjata Tradisional-Parang Salawaku', 'Senjata Tradisional', 'Parang Salawaku');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Suku-Ambon', 'Suku', 'Ambon');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (30, 'Tarian Tradisional-Lenso', 'Tarian Tradisional', 'Lenso');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Alat Musik Tradisional-Fu', 'Alat Musik Tradisional', 'Fu');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Bandar Udara-Babullah', 'Bandar Udara', 'Babullah');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Fauna-Burung Bidadari', 'Fauna', 'Burung Bidadari');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Flora-Cengkeh', 'Flora', 'Cengkeh');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Makanan Khas Daerah-Ketam Kenari', 'Makanan Khas Daerah', 'Ketam Kenari');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Obyek Wisata-Benteng Oranye', 'Obyek Wisata', 'Benteng Oranye');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Pahlawan-Sultan Babullah', 'Pahlawan', 'Sultan Babullah');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Pakaian Adat-Maluku', 'Pakaian Adat', 'Maluku');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Pelabuhan Laut-Ternate', 'Pelabuhan Laut', 'Ternate');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Rumah Adat-Baileo', 'Rumah Adat', 'Baileo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Senjata Tradisional-Parang Salawaku', 'Senjata Tradisional', 'Parang Salawaku');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Suku-Ternate', 'Suku', 'Ternate');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (31, 'Tarian Tradisional-Perang', 'Tarian Tradisional', 'Perang');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Alat Musik Tradisional-Tifa', 'Alat Musik Tradisional', 'Tifa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Bandar Udara-Sentani', 'Bandar Udara', 'Sentani');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Fauna-Burung Cendrawasih', 'Fauna', 'Burung Cendrawasih');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Flora-Matoa', 'Flora', 'Matoa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Makanan Khas Daerah-Aunu Kerang', 'Makanan Khas Daerah', 'Aunu Kerang');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Obyek Wisata-Danau Sentani', 'Obyek Wisata', 'Danau Sentani');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Pahlawan-Frans Kaiseipo', 'Pahlawan', 'Frans Kaiseipo');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Pakaian Adat-Asmat', 'Pakaian Adat', 'Asmat');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Pelabuhan Laut-Jayapura', 'Pelabuhan Laut', 'Jayapura');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Perguruan Tinggi-Universitas Cendrawasih', 'Perguruan Tinggi', 'Universitas Cendrawasih');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Rumah Adat-Honai', 'Rumah Adat', 'Honai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Senjata Tradisional-Pisau Belati', 'Senjata Tradisional', 'Pisau Belati');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Suku-Asmat', 'Suku', 'Asmat');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (32, 'Tarian Tradisional-Musyoh', 'Tarian Tradisional', 'Musyoh');" +

					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Alat Musik Tradisional-Guoto', 'Alat Musik Tradisional', 'Guoto');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Bandar Udara-Torea', 'Bandar Udara', 'Torea');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Flora-Matoa', 'Flora', 'Matoa');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Makanan Khas Daerah-Papeda', 'Makanan Khas Daerah', 'Papeda');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Makanan Khas Daerah-Sop Hapire', 'Makanan Khas Daerah', 'Sop Hapire');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Pakaian Adat-Serui', 'Pakaian Adat', 'Serui');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Pelabuhan Laut-Manokwari', 'Pelabuhan Laut', 'Manokwari');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Rumah Adat-Honai', 'Rumah Adat', 'Honai');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Suku-Dani', 'Suku', 'Dani');" +
					" INSERT INTO " + tabelGambar + "(id_prov, nama, kategori, jawaban ) VALUES (33, 'Tarian Tradisional-Selamat Datang', 'Tarian Tradisional', 'Selamat Datang');";

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
