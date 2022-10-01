/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contohdb1;

import java.sql.Date;

/**
 *
 * @author acer
 */
public class NilaiModel {
    private String Npm, KodeMk, NamaSiswa, NamaMK;
    private Date Tanggal;
    private float Nilai;
    private int Hadir, Sks, Praktek;
    private char na;

    public char getNa() {
        if(Nilai>=85) na = 'A';
        else if(Nilai>=75) na = 'B';
        else if(Nilai>=60) na = 'C';
        else if(Nilai>=40) na='D';
        else na='E';
        return(na);
    }

    public String getNpm() {
        return Npm;
    }

    public void setNpm(String Npm) {
        this.Npm = Npm;
    }

    public String getKodeMk() {
        return KodeMk;
    }

    public void setKodeMk(String KodeMk) {
        this.KodeMk = KodeMk;
    }

    public Date getTanggal() {
        return Tanggal;
    }

    public void setTanggal(Date Tanggal) {
        this.Tanggal = Tanggal;
    }

    public float getNilai() {
        return Nilai;
    }

    public void setNilai(float Nilai) {
        this.Nilai = Nilai;
    }

    public int getHadir() {
        return Hadir;
    }

    public void setHadir(int Hadir) {
        this.Hadir = Hadir;
    }
    
    public String getNamaSiswa() {
        return NamaSiswa;
    }

    public void setNamaSiswa(String NamaSiswa) {
        this.NamaSiswa = NamaSiswa;
    }
    
    public String getNamaMK() {
        return NamaMK;
    }

    public void setNamaMK(String NamaMK) {
        this.NamaMK = NamaMK;
    }

    public int getSks() {
        return Sks;
    }

    public void setSks(int Sks) {
        this.Sks = Sks;
    }

    public int getPraktek() {
        return Praktek;
    }

    public void setPraktek(int Praktek) {
        this.Praktek = Praktek;
    }
}
