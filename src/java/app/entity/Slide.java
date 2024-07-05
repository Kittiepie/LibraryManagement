/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.entity;

/**
 *
 * @author OwO
 */
public class Slide {
    
    private int silde_id;
    private String title;
    private String image;
    private String backlink;
    private int authorId;
    private String description;
    private boolean active;

    public Slide() {
    }

    public Slide(int silde_id, String title, String image, String backlink, int authorId, String description, boolean active) {
        this.silde_id = silde_id;
        this.title = title;
        this.image = image;
        this.backlink = backlink;
        this.authorId = authorId;
        this.description = description;
        this.active = active;
    }
    
    

    public Slide(int silde_id, String title, String image, String backlink, String description, boolean active) {
        this.silde_id = silde_id;
        this.title = title;
        this.image = image;
        this.backlink = backlink;
        this.description = description;
        this.active = active;
    }

    public Slide(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public Slide(String title, String image, String description, String backlink) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.backlink = backlink;

    }

    public int getSilde_id() {
        return silde_id;
    }

    public void setSilde_id(int silde_id) {
        this.silde_id = silde_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }


    
}
