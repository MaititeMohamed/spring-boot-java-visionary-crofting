package com.youcode.visionarycrofting.entity;




import javax.persistence.*;
import java.util.List;


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
 @OneToMany(mappedBy = "command")
private List<ItemCommand> listItem;

 public Command(Long id, String ref, String dateTime, int totalPrice, String address, List<ItemCommand> listItem) {
  this.id = id;
  this.ref = ref;
  this.dateTime = dateTime;
  this.totalPrice = totalPrice;
  this.address = address;
  this.listItem = listItem;
 }

 public Command(String ref, String dateTime, int totalPrice, String address, List<ItemCommand> listItem) {
  this.ref = ref;
  this.dateTime = dateTime;
  this.totalPrice = totalPrice;
  this.address = address;
  this.listItem = listItem;
 }

 public List<ItemCommand> getListItem() {
  return listItem;
 }

 public void setListItem(List<ItemCommand> listItem) {
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
