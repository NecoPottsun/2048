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

    private AccountDao(){
        excelAcess("data/account.xls");
        // Achieve account list
        getAll();
    }

    /**
     AccountDao.java follows the Singleton, because it only need to be
     created once
     */
    // Apply Singleton for AccountDao
    public static AccountDao getInstance(){
        if(instance == null){
            instance = new AccountDao();
        }
        return instance;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     Find the account in the AccountList by using the account's id
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
     Retrieve all the account data from the Excel file
     * */
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
     * Add a new account to the excel file
     * @param account
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
     * @param account
     * @return
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
     * @param accountList
     */
    public void PrintList(List<Account> accountList){
        for(Account account : accountList){
            System.out.println(account.toString());
        }
    }

    /**
     * The method is to overwrite the excel file.
     * Whenever there is any change to the list of account
     * , it is used to update the data in the excel file.
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
     *This function is to open the excel file and then read
     * every data in the first sheet
     * */
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
     * @return accounts
     */
    public List<Account> GetSortList(){
        Collections.sort(accounts);
        return accounts;
    }



}
