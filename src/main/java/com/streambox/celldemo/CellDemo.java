package com.streambox.celldemo;

/* Copyright (c) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.mail.*;
import javax.security.*;
import javax.mail.Service;

import com.google.common.collect.*;
import com.google.gdata.client.*;
import com.google.gdata.client.spreadsheet.CellQuery;
import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.ListQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetQuery;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.BaseEntry;
import com.google.gdata.data.Link;
import com.google.gdata.data.batch.BatchOperationType;
import com.google.gdata.data.batch.BatchStatus;
import com.google.gdata.data.batch.BatchUtils;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.net.*;


/**
 * Using this demo, you can see how GData can read and write to individual cells
 * based on their position or send a batch of update commands in one HTTP
 * request.
 *
 * Usage: java CellDemo --username [user] --password [pass]
 */
public class CellDemo {

  /** The message for displaying the usage parameters. */
  private static final String[] USAGE_MESSAGE = {
      "Usage: java CellDemo --username [user] --password [pass] ", ""};

  /** Welcome message, introducing the program. */
  private static final String[] WELCOME_MESSAGE = {
      "This is a demo of the cells feed!", "",
      "Using this interface, you can read/write to your spreadsheet's cells.",
      ""};

  /** Help on all available commands. */
  private static final String[] COMMAND_HELP_MESSAGE = {
      "Commands:",
      " load                              "
          + "[[select a spreadsheet and worksheet]]",
      " list                              [[shows all cells]]",
      " range minRow maxRow minCol maxCol [[rectangle]]",
      " set row# col# formula             [[sets a cell]]",
      "   example: set 1 3 =R1C2+1",
      " search adam                       [[full text query]]",
      " batch                             [[batch request]]",
      " exit"};


  /** Our view of Google Spreadsheets as an authenticated Google user. */
  private SpreadsheetService service;

  /** The URL of the cells feed. */
  private URL cellFeedUrl;

  /** The output stream. */
  private PrintStream out;

  /** A factory that generates the appropriate feed URLs. */
  private FeedURLFactory factory;

  /**
   * Constructs a cell demo from the specified spreadsheet service, which is
   * used to authenticate to and access Google Spreadsheets.
   *
   * @param service the connection to the Google Spradsheets service.
   * @param outputStream a handle for stdout.
   */
  public CellDemo(SpreadsheetService service, PrintStream outputStream) {
    this.service = service;
    this.out = outputStream;
    this.factory = FeedURLFactory.getDefault();
  }

  /**
   * Log in to Google, under the Google Spreadsheets account.
   *
   * @param username name of user to authenticate (e.g. yourname@gmail.com)
   * @param password password to use for authentication
   * @throws AuthenticationException if the service is unable to validate the
   *         username and password.
   */
  public void login(String username, String password)
      throws AuthenticationException {

    // Authenticate
    service.setUserCredentials(username, password);
  }

  /**
   * Displays the given list of entries and prompts the user to select the index
   * of one of the entries. NOTE: The displayed index is 1-based and is
   * converted to 0-based before being returned.
   *
   * @param reader to read input from the keyboard
   * @param entries the list of entries to display
   * @param type describes the type of things the list contains
   * @return the 0-based index of the user's selection
   * @throws IOException if an I/O error occurs while getting input from user
   */
  private int getIndexFromUser(BufferedReader reader, List entries, String type)
      throws IOException {
    for (int i = 0; i < entries.size(); i++) {
      BaseEntry entry = (BaseEntry) entries.get(i);
      System.out.println("\t(" + (i + 1) + ") "
          + entry.getTitle().getPlainText());
    }
    int index = -1;
    while (true) {
      out.print("Enter the number of the spreadsheet to load: ");
      String userInput = reader.readLine();
      try {
        index = Integer.parseInt(userInput);
        if (index < 1 || index > entries.size()) {
          throw new NumberFormatException();
        }
        break;
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid number for your selection.");
      }
    }
    return index - 1;
  }

  /**
   * Uses the user's credentials to get a list of spreadsheets. Then asks the
   * user which spreadsheet to load. If the selected spreadsheet has multiple
   * worksheets then the user will also be prompted to select what sheet to use.
   *
   * @param reader to read input from the keyboard
   * @throws ServiceException when the request causes an error in the Google
   *         Spreadsheets service.
   * @throws IOException when an error occurs in communication with the Google
   *         Spreadsheets service.
   *
   */
 

  /**
   * Returns a CellEntry with batch id and operation type that will tell the
   * server to update the specified cell with the given value. The entry is
   * fetched from the server in order to get the current edit link (for
   * optimistic concurrency).
   *
   * @param row the row number of the cell to operate on
   * @param col the column number of the cell to operate on
   * @param value the value to set in case of an update the cell to operate on
   *
   * @throws ServiceException when the request causes an error in the Google
   *         Spreadsheets service.
   * @throws IOException when an error occurs in communication with the Google
   *         Spreadsheets service.
   */
  private CellEntry createUpdateOperation(int row, int col, String value)
      throws ServiceException, IOException {
    String batchId = "R" + row + "C" + col;
    URL entryUrl = new URL(cellFeedUrl.toString() + "/" + batchId);
    CellEntry entry = service.getEntry(entryUrl, CellEntry.class);
    entry.changeInputValueLocal(value);
    BatchUtils.setBatchId(entry, batchId);
    BatchUtils.setBatchOperationType(entry, BatchOperationType.UPDATE);

    return entry;
  }

  /**
   * Runs the demo.
   *
   * @param args the command-line arguments
 * @throws ServiceException 
 * @throws IOException 
   */
  public static void main(String[] args) throws IOException, ServiceException {
    //SimpleCommandLineParser parser = new SimpleCommandLineParser(args);
    //String username = parser.getValue("username", "user", "u");
    //String password = parser.getValue("password", "pass", "p");
    
	  String username = args[0];
	  String password = args[1];
    
    //boolean help = parser.containsKey("help", "h");

    if (username == null || password == null) {
      usage();
      System.exit(1);
    }

    SpreadsheetService service = new SpreadsheetService("Cell Demo");
    service.setUserCredentials(username,password);
    
    
    FeedURLFactory urlFactory = FeedURLFactory.getDefault();
    SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(urlFactory.getSpreadsheetsFeedUrl());
    
    spreadsheetQuery.setTitleQuery("xpath for live/es");  //Set the name of spreadsheet which we would like to use
    SpreadsheetFeed spreadsheetFeed = service.query(spreadsheetQuery,SpreadsheetFeed.class);
    
    SpreadsheetEntry spreadsheetEntry = spreadsheetFeed.getEntries().get(0);
    
    System.out.println("Name:" + spreadsheetEntry.getTitle().getPlainText());
    
    //get worksheet which I am looking for
    WorksheetEntry worksheetEntry = spreadsheetEntry.getDefaultWorksheet();
    
    
    //Seraching  inside wirksheet
    
    
    /*
    ListQuery listQuery = new ListQuery(worksheetEntry.getListFeedUrl());
    int rowCount=1;
    listQuery.setStartIndex(rowCount);
    listQuery.setMaxResults(1);
    ListFeed listFeed = service.query(listQuery, ListFeed.class);
    //ListFeed listFeed = service.query(listQuery, ListFeed.class);
    
    
    List<ListEntry> entries = listFeed.getEntries();
    
   ListEntry listEntry = entries.get(0);
   
  listEntry.delete();
  */
    
    
    //*******************Declaration for userclass and hashtable**************************************//
    UserandEslstypeClass  userclass[] = new UserandEslstypeClass[23];
    //Hashtable<Key="xpath", UserandEslstypeClass>
    //Hashtable hashtable = new Hashtable();
    //HashMap<String, UserandEslstypeClass> hashtable = new HashMap<String, UserandEslstypeClass>();
    
    //ArrayList for storing above Hash
    ArrayList<HashMap<String, UserandEslstypeClass>> arraylist = new ArrayList<HashMap<String, UserandEslstypeClass>>();
    //**************************************************************//
    
    ListQuery listQuery = new ListQuery(worksheetEntry.getListFeedUrl());
    
    for(int i=1; i<23;i++){
        
    listQuery.setSpreadsheetQuery("label ="+i+"");
    
    //int rowCount=1;
    //listQuery.setStartIndex(rowCount);
    //listQuery.setMaxResults(1);
    ListFeed listFeed = service.query(listQuery,ListFeed.class);
    ListEntry listEntry = listFeed.getEntries().get(0);
    CustomElementCollection elements = listEntry.getCustomElements();
    
       
    //System.out.println("Refer to:   " + elements.getValue(""+ i +""));
    
    
    String type = elements.getValue("eslsType");
    String typefront=null;
    String typebehind=null;
    ArrayList<ArrayList<String>> storage = new ArrayList<ArrayList<String>>();
    
    int start = 0,end=type.length();
    Divide(type, storage,start,end);
   
    
    //System.out.println("Storage: "+storage);
    
    userclass[i] = new UserandEslstypeClass(elements.getValue("label"), elements.getValue("systemadminShouldSee"),elements.getValue("contributor"), elements.getValue("groupadmin"), storage);
    
    //userclass[i] = new UserandEslstypeClass(elements.getValue("label"), elements.getValue("systemadminShouldSee"),elements.getValue("contributor"), elements.getValue("groupadmin"), elements.getValue("eslsType"));
    
    
    //System.out.println("Refer to:   " + elements.getValue("label"));
    //System.out.println("Refer to:   " + elements.getValue("eslsType"));
    //System.out.println("Refer to:   " + elements.getValue("xpath"));
   // System.out.println("Refer to:   " + elements.getValue("Livw1"));
    HashMap<String, UserandEslstypeClass> hashtable = new HashMap<String, UserandEslstypeClass>();
    
    
    hashtable.put(elements.getValue("xpath"), userclass[i]);
    
    //System.out.println("Refer to:   " + hashtable.get(elements.getValue("xpath")).getlabel());
    
    //arraylist[i] = new ArrayList<HashMap<String, UserandEslstypeClass>>();
    arraylist.add(hashtable);
    
    
    }// end of for loop
  
    
    /*
    for(int i=1;i<arraylist.size();i++){
    	
    	//System.out.println("ArrayList:   " + arraylist.get(i));	
    }
    */
    
    HashMap<String, UserandEslstypeClass>  hash = arraylist.get(0);
    System.out.println(hash.keySet());
    Set<String> key1Set=hash.keySet();
    String key1 = key1Set.toString();
    System.out.println(key1);
    System.out.println(hash.get("//a[text()='Default Routing Protocol: LDMP']").getrslstype().get(1));
    
    
  List f =null;
  Service s =null;

  //Hashtable<String, ArrayList<Object>> hashtable = Hashtable<String, ArrayLis<Object>>();
  
  
  
  
  
    
	  
  }	

private static void Divide(String type,ArrayList<ArrayList<String>> allset, int start, int end) {
	if(type==null||start==end||start==end-1)
		return;
	
	ArrayList<String> subset= new ArrayList<String>();
	int current = start;
	
	while(type.charAt(current)!=','){
		current++;
		if(current >= end){
			break;
		}
	}
	
	
		subset.add(type.substring(start,current));
	
	
	if(current!=end){
		start = current+2;
		while(start ==' ')
			start++;
	}else{
		start = current;
	}
	
	allset.add(subset);
	Divide(type, allset,start,end);
	
	return;	
}// end of Dvide()


/**
   * Prints out the usage.
   */
  private static void usage() {
    for (String s : USAGE_MESSAGE) {
      System.out.println(s);
    }
    for (String s : WELCOME_MESSAGE) {
      System.out.println(s);
    }
  }
}
