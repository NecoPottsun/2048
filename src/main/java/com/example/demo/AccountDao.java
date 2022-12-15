package com.example.demo;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AccountDao.class is used to handle the functions that achieve data from the excel file
 * as well as write data to the file, so that we can also add and delete a specific account entity
 * in the file.
 * @author Thanh Mai Duyen Trang
 */
public class AccountDao implements Dao<Account>{


    private List<Account> accounts = new ArrayList<>();
    private static AccountDao instance;

    private static HSSFWorkbook workbook;
    private HSSFSheet sheet;

    private File file;
    private FileInputStream fis;
    private FileOutputStream out;

    private int rowNumber = 0;
    private int cellNumber = 0;

    /**
     * The constructor of AccountDao
     */
    private AccountDao(){
        excelAcess("data/account.xls");
        // Achieve account list
        getAll();
    }

    /**
     * <code>getInstance()</code>help this AccountDao follows the Singleton, because it only needs to be
     *  created once
     * @return AccountDao
     * */
    // Apply Singleton for AccountDao
    public static AccountDao getInstance(){
        if(instance == null){
            instance = new AccountDao();
        }
        return instance;
    }

    /**
     * Gets the Account list of this AccountDao
     * @return List of account of this AccountDao
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Gets the workbook of this AccountDao
     * @return HSSFWorkbook
     */
    public static HSSFWorkbook getWorkbook() {
        return workbook;
    }

    /**
     * Gets the sheet inside the Excel file of this AccountDao
     * @return HSSFSheet
     */
    public HSSFSheet getSheet() {
        return sheet;
    }

    /**
     * Gets the Excel file of this AccountDao
     * @return File
     */
    public File getFile() {
        return file;
    }

    /**
     * Gets the FileInputStream of this AccountDao
     * @return FileInputStream
     */
    public FileInputStream getFis() {
        return fis;
    }

    /**
     * Gets the FileOutputStream of this AccountDao
     * @return FileOutputStream
     */
    public FileOutputStream getOut() {
        return out;
    }

    /**
     * Gets the number of rows inside the Excel sheet of this AccountDao
     * @return rowNumber
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * Gets the number of cells inside the excel sheet of this AccountDao
     * @return cellNumber
     */
    public int getCellNumber() {
        return cellNumber;
    }

    /**
     * Find the account in the AccountList by using the account's id
     * @param id the id of the account that want to search for
     * @return <Code>Account</Code> if the id exists in the Account List of this AccountDao;
     *          <Code>null</Code> if the id doesn't exist in the Account List;
     */
    @Override
    public Account findById(long id) {
        for(Account account : accounts){
            if(account.getId() == id){
//                System.out.println("Account achieved");
                return account;
            }
        }
        System.out.println("No account achieved");
        return null;
    }

    /**
     * Retrieve all the account data from sheet in the Excel file of this AccountDao
     * @return List of account
     */
    @Override
    public List<Account> getAll() {
        for(Row row: sheet)
        {
            rowNumber++;
            // skip the first row
            if(row.getRowNum() != 0){
                Account account = new Account();
                //iteration over cell using for each loop
                for (org.apache.poi.ss.usermodel.Cell cell : row)
                {
                    cellNumber++;
                    // id column
                    if(cell.getColumnIndex() == 0){
 //                       System.out.println("id column: " + cell.getNumericCellValue());
                        long id = (new Double(cell.getNumericCellValue())).longValue();
                        account.setId(id);
                    }
                    // username column
                    if(cell.getColumnIndex() == 1){
 //                       System.out.println("username column: " + cell.getStringCellValue());
                        String username = cell.getStringCellValue();
                        account.setUsername(username);
                    }
                    // score column
                    if(cell.getColumnIndex() == 2){
 //                       System.out.println("score column: " + cell.getNumericCellValue());
                        double score = cell.getNumericCellValue();
                        account.setScore(score);
                    }
                    if(cell.getColumnIndex() == 3){
                        String playTime = cell.getStringCellValue();
                        account.setPlayTime(playTime);
                    }

                }
                accounts.add(account);
            }

        }
        Collections.sort(accounts);
        // sort arrays
        PrintList(accounts);
        return accounts;
    }

    /**
     * Add a new account to sheet in the excel file
     * @param account a new account that want to add
     */
    @Override
    public void add(Account account) {
        // create a new row
        HSSFRow row = sheet.createRow(rowNumber + 1);

        // Create new cell  and put the data into the cell
        row.createCell(0);
        row.getCell(0).setCellValue(account.getId());
        row.createCell(1);
        row.getCell(1).setCellValue(account.getUsername());
        row.createCell(2);
        row.getCell(2).setCellValue(account.getScore());
        row.createCell(3);
        row.getCell(3).setCellValue(account.getPlayTime());

        updatedExcelFile();
    }


    /**
     * delete an account in the excel file
     * @param account the account that want be deleted
     * @return <code>True</code> found the account inside the account list and delete successful;
     *          <code>False</code> the account not found in the account list, delete failed
     */
    @Override
    public Boolean delete(Account account) {
        for(Row row : sheet){
            // skip the first row
            if(row.getRowNum() != 0){
                long id = (new Double((row.getCell(0)).getNumericCellValue())).longValue();
                if(account.getId() == id){
                    System.out.println( account.getUsername() + " deleted");
                    sheet.removeRow(row);
                    updatedExcelFile();

                    return true;
                }


            }

        }
        return false;

    }

    /**
     * Print out all the accounts in the list in the system
     * @param accountList the list of account that want to be printed
     */
    public void PrintList(List<Account> accountList){
        for(Account account : accountList){
            System.out.println(account.toString());
        }
    }

    /**
     * Overwrites the Excel file.
     * Whenever there is any change to the list of account
     * , it is used to update the data in the Excel file.
     */
    private void updatedExcelFile() {
        try {
            out = new FileOutputStream(file);
            workbook.write(out);
 //           workbook.close();
            System.out.println("Excel file updated");
            out.flush();
            out.close();
            // Update the Account list
            accounts.clear();
            getAll();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *  Opens the Excel file and then read every data in the first sheet
     * @param path the location of the Excel file
     */
    private void excelAcess(String path){
        URL url = getClass().getResource(path);
        try {
            file = new File(url.toURI());
            if(!file.exists()){
                file.createNewFile();
            }
            fis=new FileInputStream(file);
            //creating workbook instance that refers to .xls file
            workbook =new HSSFWorkbook(fis);
            //creating a Sheet object to retrieve the object
            sheet= workbook.getSheetAt(0);
            HSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Sort the account list by score in DESCENDING order
     * @return the list of account that is sorted
     */
    public List<Account> GetSortList(){
        Collections.sort(accounts);
        return accounts;
    }



}
