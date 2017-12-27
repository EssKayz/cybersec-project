/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project;

/**
 *
 * @author ColdFish
 */
public class Address {
    private int id;
    private String name;
    private String address;

    public Address(String name, String address, int id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    
    @Override
    public String toString(){
        return this.name + ", " + this.address;
    }
    
    
    
    
}
