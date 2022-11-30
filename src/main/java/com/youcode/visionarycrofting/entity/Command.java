package com.youcode.visionarycrofting.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
public class Command {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private  String ref;

 private String dateTime;
 private double totalPrice ;
 private String address;

 @ManyToOne
 private Client client;
 @OneToMany(mappedBy = "command",fetch = FetchType.EAGER)
 private List<CommandItem> listItem = new ArrayList <> (  );

 public Command(Long id, String ref, String dateTime, double totalPrice, String address, List<CommandItem> listItem) {
  this.id = id;
  this.ref = ref;
  this.dateTime = dateTime;
  this.totalPrice = totalPrice;
  this.address = address;
  this.listItem = listItem;
 }

 public Command(String ref, String dateTime, double totalPrice, String address, List<CommandItem> listItem) {
  this.ref = ref;
  this.dateTime = dateTime;
  this.totalPrice = totalPrice;
  this.address = address;
  this.listItem = listItem;
 }

 public List<CommandItem> getListItem() {
  return listItem;
 }

 public void setListItem(List<CommandItem> listItem) {
  this.listItem = listItem;
 }

 public Command() {

 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getRef() {
  return ref;
 }

 public void setRef(String ref) {
  this.ref = ref;
 }

 public String getDateTime() {
  return dateTime;
 }

 public void setDateTime(String dateTime) {
  this.dateTime = dateTime;
 }

 public double getTotalPrice() {
  return totalPrice;
 }

 public void setTotalPrice(double totalPrice) {
  this.totalPrice = totalPrice;
 }

 public String getAddress() {
  return address;
 }

 public void setAddress(String address) {
  this.address = address;
 }

 public void setItem(CommandItem commandItem){
  this.listItem.add(commandItem);
 }

 @Override
 public String toString() {
  return "Command{" +
          "id=" + id +
          ", ref='" + ref + '\'' +
          ", dateTime='" + dateTime + '\'' +
          ", totalPrice=" + totalPrice +
          ", address='" + address + '\'' +
          ", listItem=" + listItem +
          '}';
 }
}