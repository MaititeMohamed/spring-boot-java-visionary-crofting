package com.youcode.visionarycrofting.entity;




import javax.persistence.*;




@Entity
@Table
public class Command {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private  String ref;

 private String dateTime;
 private int totalPrice ;
 private String address;
 //private List<Object> listItem;


 public Command(Long id, String ref, String dateTime, int totalPrice, String address) {
  this.id = id;
  this.ref = ref;
  this.dateTime = dateTime;
  this.totalPrice = totalPrice;
  this.address = address;
 }

 public Command(String ref, String dateTime, int totalPrice, String address) {
  this.ref = ref;
  this.dateTime = dateTime;
  this.totalPrice = totalPrice;
  this.address = address;
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

 public int getTotalPrice() {
  return totalPrice;
 }

 public void setTotalPrice(int totalPrice) {
  this.totalPrice = totalPrice;
 }

 public String getAddress() {
  return address;
 }

 public void setAddress(String address) {
  this.address = address;
 }
}
