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
    private String Npm, KodeMk;
    private Date Tanggal;
    private float Nilai;
    private int Hadir;

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
}
