package comp3350.ims.business;

import java.util.ArrayList;

import comp3350.ims.application.Main;
import comp3350.ims.application.Services;
import comp3350.ims.objects.Inventory;
import comp3350.ims.objects.Item;
import comp3350.ims.objects.ItemType;
import comp3350.ims.persistence.DataAccess;


public class AccessInventory {
	private static boolean isManager = false;
	private  DataAccess dataAccess;
	private static Inventory activeInventory;
	private static int currentItemPosition;

	public AccessInventory() {
		dataAccess =  Services.getDataAccess(Main.dbName);

		if(activeInventory == null) {
			activeInventory = dataAccess.getActiveInventory();
		}

	}

	public AccessInventory(boolean reset) {
		dataAccess =  Services.getDataAccess(Main.dbName);

		if(activeInventory == null || reset) {
			activeInventory = dataAccess.getActiveInventory();
		}

	}

	public Inventory getActiveInventory() {
		return activeInventory;
	}

	public boolean insertItemType(String nameString, float price, int quantity, String locationString, String thisDate,String categoryString) {

		boolean isInserted = true;
		ItemType newItemType = new ItemType(nameString,price,locationString,thisDate,categoryString);
		if(activeInventory.contains(newItemType)) {
			isInserted = false;
		} else{
			activeInventory.addItem(newItemType);
			dataAccess.insertItem(newItemType);
			ArrayList<Item> items = new ArrayList<>();
			for(int i = 0; i < quantity; i++){
				Item newItem = new Item(locationString,thisDate);
				dataAccess.addItem(newItem, newItemType.getID());
				newItemType.addItem(newItem);
			}

		}

		return isInserted;

	}

	public void setCurrentItem(int index) {
		currentItemPosition = index;
	}

	public int getCurrentItemPosition() {
		return currentItemPosition;
	}

	public ItemType getItem(int index) {
		return activeInventory.getItem(index);
	}

	public String getCategories(ArrayList<String> categoryList) {
		categoryList.clear();
		return dataAccess.getCategoryList(categoryList);
	}

	public String getLocations(ArrayList<String> categoryList) {
		categoryList.clear();
		return dataAccess.getLocationList(categoryList);
	}

	public void addItem(String location, String date, ItemType itemType){
		Item newItem = new Item(location,date);
		itemType.addItem(newItem);
		dataAccess.addItem(newItem,itemType.getID());
	}


	public void addCategory(String category) {
		if (category != null) {
			dataAccess.addCategory(category);
		}
	}

	public void addLocation(String location) {
		if (location != null) {
			dataAccess.addLocation(location);
		}
	}

	public boolean removeLocation(String name){

		return dataAccess.removeLocation(name);
	}
	public boolean removeCategory (String name){
		return dataAccess.removeCategory(name);

	}
		public void removeIndividualItem ( int index){
			ItemType itemType = getItem(currentItemPosition);
			if (index >= 0 && itemType.getQuantity() > 0) {
				Item item = itemType.getItem(index);
				itemType.removeItem(index);
				dataAccess.removeItem(item.getId(),itemType.getID(),itemType.getQuantity());
			}

		}
	public boolean isCategory(String name){
		return dataAccess.isCategory(name) ;
	}
	public boolean isLocation(String name){
		return dataAccess.isLocation(name) ;
	}
	public boolean editItemType(ItemType itemType,String name,float price,String category){
		return dataAccess.editItemType(itemType,name,price,category);
	}
	public boolean editItem(Item item,String location){ return dataAccess.editItem(item,location); }

	public static boolean isIsManager() {
		return isManager;
	}

	public static void setIsManager(boolean isManager) {
		AccessInventory.isManager = isManager;
	}

}
