package com.dsoft.controller.admin;

import com.dsoft.entity.*;
import com.dsoft.service.AdminJdbcService;
import com.dsoft.service.AdminService;
import com.dsoft.util.Constants;
import com.dsoft.util.Utils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes({"", ""})
public class AdminController {

    private static Logger logger = Logger.getLogger(AdminController.class);
    @Autowired(required = true)
    private AdminService adminService;
    @Autowired(required = true)
    private AdminJdbcService adminJdbcService;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }


    @RequestMapping(value = "/*/upsertPerson.do", method = RequestMethod.GET)
    public String getPerson(HttpServletRequest request, Model model) {
        logger.error("SMNLOG: :: upsert Person controller :: ");
        Long personId = request.getParameter("personId") != null ? Long.parseLong(request.getParameter("personId")) : 0;
        Person person = new Person();
        try {
            if (personId > 0) {
                person = adminService.getPerson(personId);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (person == null) {
            logger.debug("ERROR:Failed to load person");
            Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.load.failed.msg"));
            return "redirect:./userList.do";
        }

        model.addAttribute("person", person);
        return "common/person";
    }


    @RequestMapping(value = "/*/upsertPerson.do", method = RequestMethod.POST)
    public String savePerson(HttpServletRequest request, @ModelAttribute("person") Person person) {
        logger.debug("SMNLOG:: Person POST Controller::");
        Long personId = person.getId();
        try {
            logger.debug("SMNLOG:: person:: " + person);

            adminService.saveOrUpdateObject(person);

            if (personId != null)
                Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("person.update.success.msg"));
            else
                Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("person.save.success.msg"));
        } catch (Exception ex) {
            logger.error("Save user exception:: " + ex);
            if (personId != null)
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.update.failed.msg"));
            else
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.save.failed.msg"));

        }
        return "redirect:./personList.do";
    }

    @RequestMapping(value = "/*/deletePerson.do", method = RequestMethod.GET)
    public String deleteperson(HttpServletRequest request, Model model) {
        logger.error("SMNLOG: :: delete person controller :: ");
        Long personId = request.getParameter("personId") != null ? Long.parseLong(request.getParameter("personId")) : 0;
        Person person = new Person();
        try {
            if (personId > 0) {
                person = adminService.getPerson(personId);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (person == null) {
            logger.debug("ERROR:Failed to load person");
            Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.load.failed.msg"));
            return "redirect:./personList.do";
        } else {

            try {
                adminService.deletePerson(person);
                Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("person.delete.success.msg"));
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("ERROR:Failed to delete person");
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.delete.failed.msg"));
                return "redirect:./personList.do";
            }
        }

        model.addAttribute("person", person);

        return "redirect:./personList.do";
    }

    @RequestMapping(value = "/*/personList.do", method = RequestMethod.GET)
    public String personListView(HttpServletRequest request, Model model) {
        logger.error("SMNLOG: :: person view controller :: ");

        return "common/personList";
    }

    @RequestMapping(value = "/*/getPersons.do", method = RequestMethod.GET)
    public
    @ResponseBody
    DataModelBean getPersons(HttpServletRequest request) throws Exception {
        logger.info(":: Get Person List Ajax Controller ::");
        DataModelBean dataModelBean = new DataModelBean();
    /* this params is for dataTables */
        String[] tableColumns = "p.name,p.mobile_no,p.address".split(",");
        int start = request.getParameter(Constants.IDISPLAY_START) != null ? Integer.parseInt(request.getParameter(Constants.IDISPLAY_START)) : 0;
        int length = request.getParameter(Constants.IDISPLAY_LENGTH) != null ? Integer.parseInt(request.getParameter(Constants.IDISPLAY_LENGTH)) : 5;
        int sEcho = request.getParameter(Constants.sEcho) != null ? Integer.parseInt(request.getParameter(Constants.sEcho)) : 0;
        int iSortColIndex = request.getParameter(Constants.iSortCOL) != null ? Integer.parseInt(request.getParameter(Constants.iSortCOL)) : 0;
        String searchKey = request.getParameter(Constants.sSearch) != null ? request.getParameter(Constants.sSearch) : "";
        String sortType = request.getParameter(Constants.sortType) != null ? request.getParameter(Constants.sortType) : "asc";
        String sortColName = "";
        logger.debug("SMNLOG:iSortColIndex:" + iSortColIndex + " sortType:" + sortType + " searchKey:" + searchKey);

        // sorting related operation for data Tables

        sortColName = tableColumns[iSortColIndex];
        logger.debug("SMNLOG:sortColName:" + sortColName);

        String trackingDetailsDataStr = null;
        Map<String, Object> userDataMap;

        try {
            int totalRecords = adminService.getPersonEntitySize();
            if (length < 0) {
                userDataMap = adminJdbcService.getPersons(start, totalRecords, sortColName, sortType, searchKey);
            } else {
                userDataMap = adminJdbcService.getPersons(start, length, sortColName, sortType, searchKey);
            }


                /*
                * DataModelBean is a bean of Data table to
                * handle data Table search, paginatin operation very simply
                */
            dataModelBean.setAaData((List) userDataMap.get("data"));

            if (!Utils.isEmpty(searchKey)) {
                dataModelBean.setiTotalDisplayRecords((Integer) userDataMap.get("total"));
            } else {
                dataModelBean.setiTotalDisplayRecords(totalRecords);
            }
            dataModelBean.setiTotalRecords(totalRecords);
            dataModelBean.setsEcho(sEcho);
            dataModelBean.setiDisplayStart(start);
            dataModelBean.setiDisplayLength(totalRecords);

        } catch (Exception ex) {
            logger.error(":: ERROR:: Failed to load person details data:: " + ex);
        }

        return dataModelBean;
    }

    @RequestMapping(value = "/*/upsertGroup.do", method = RequestMethod.GET)
    public String getGroup(HttpServletRequest request, Model model) {
        logger.debug("***************  Group Upsert Controller ***************");
        Group group = null;
        List<Person> personList = new ArrayList<Person>();
        List<GroupMember> groupPersonList = new ArrayList<GroupMember>();

        Long groupId = request.getParameter("groupId") != null ? Long.parseLong(request.getParameter("groupId")) : 0;
        logger.debug("SMNLOG:groupId:" + groupId);
        try {
            if (groupId > 0) {
                group = adminService.getGroup(groupId);
                groupPersonList = adminService.getGroupPersonList(groupId);

            } else {
                group = new Group();
            }
            personList = adminService.getPersonList();
            group.setPersonList(personList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("SMNLOG:ERROR ::Group::" + e);
        }

        model.addAttribute("group", group);
        model.addAttribute("groupPersonList", groupPersonList);
        return "common/group";
    }


    @RequestMapping(value = "/*/upsertGroup.do", method = RequestMethod.POST)
    public String saveGroup(HttpServletRequest request, @ModelAttribute("group") Group group) {
        logger.debug("SMNLOG:: Group POST Controller::");
        Long groupId = group.getId();
        try {
            logger.debug("SMNLOG:: group:: " + group);

            adminService.saveOrUpdateGroup(group);

            if (groupId != null)
                Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("group.update.success.msg"));
            else
                Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("group.save.success.msg"));
        } catch (Exception ex) {
            logger.error("Save user exception:: " + ex);
            if (groupId != null)
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("group.update.failed.msg"));
            else
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("group.save.failed.msg"));

        }
        return "redirect:./groupList.do";
    }

    @RequestMapping(value = "/*/deleteGroup.do", method = RequestMethod.GET)
    public String deletegroup(HttpServletRequest request, Model model) {
        logger.error("SMNLOG: :: delete group controller :: ");
        Long groupId = request.getParameter("groupId") != null ? Long.parseLong(request.getParameter("groupId")) : 0;
        Group group = new Group();
        try {
            if (groupId > 0) {
                group = adminService.getGroup(groupId);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (group == null) {
            logger.debug("ERROR:Failed to load group");
            Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("group.load.failed.msg"));
            return "redirect:./groupList.do";
        } else {

            try {
                adminService.deleteGroup(group);
                Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("group.delete.success.msg"));
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("ERROR:Failed to delete group");
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("group.delete.failed.msg"));
                return "redirect:./groupList.do";
            }
        }

        model.addAttribute("group", group);

        return "redirect:./groupList.do";
    }

    @RequestMapping(value = "/*/groupList.do", method = RequestMethod.GET)
    public String groupListView(HttpServletRequest request, Model model) {
        logger.error("SMNLOG: :: group view controller :: ");
        return "common/groupList";
    }

    @RequestMapping(value = "/*/getGroups.do", method = RequestMethod.GET)
    public
    @ResponseBody
    DataModelBean getGroup(HttpServletRequest request) throws Exception {
        logger.info(":: Get Group List Ajax Controller ::");
        DataModelBean dataModelBean = new DataModelBean();
        /* this params is for dataTables */
        String[] tableColumns = "g.name,p.name,p.mobile_no".split(",");
        int start = request.getParameter(Constants.IDISPLAY_START) != null ? Integer.parseInt(request.getParameter(Constants.IDISPLAY_START)) : 0;
        int length = request.getParameter(Constants.IDISPLAY_LENGTH) != null ? Integer.parseInt(request.getParameter(Constants.IDISPLAY_LENGTH)) : 5;
        int sEcho = request.getParameter(Constants.sEcho) != null ? Integer.parseInt(request.getParameter(Constants.sEcho)) : 0;
        int iSortColIndex = request.getParameter(Constants.iSortCOL) != null ? Integer.parseInt(request.getParameter(Constants.iSortCOL)) : 0;
        String searchKey = request.getParameter(Constants.sSearch) != null ? request.getParameter(Constants.sSearch) : "";
        String sortType = request.getParameter(Constants.sortType) != null ? request.getParameter(Constants.sortType) : "asc";
        String sortColName = "";
        logger.debug("SMNLOG:iSortColIndex:" + iSortColIndex + " sortType:" + sortType + " searchKey:" + searchKey);

        // sorting related operation for data Tables

        sortColName = tableColumns[iSortColIndex];
        logger.debug("SMNLOG:sortColName:" + sortColName);

        String trackingDetailsDataStr = null;
        Map<String, Object> userDataMap;

        try {
            int totalRecords = adminService.getGroupEntitySize();
            if (length < 0) {
                userDataMap = adminJdbcService.getGroups(start, totalRecords, sortColName, sortType, searchKey);
            } else {
                userDataMap = adminJdbcService.getGroups(start, length, sortColName, sortType, searchKey);
            }


                /*
                * DataModelBean is a bean of Data table to
                * handle data Table search, paginatin operation very simply
                */
            dataModelBean.setAaData((List) userDataMap.get("data"));

            if (!Utils.isEmpty(searchKey)) {
                dataModelBean.setiTotalDisplayRecords((Integer) userDataMap.get("total"));
            } else {
                dataModelBean.setiTotalDisplayRecords(totalRecords);
            }
            dataModelBean.setiTotalRecords(totalRecords);
            dataModelBean.setsEcho(sEcho);
            dataModelBean.setiDisplayStart(start);
            dataModelBean.setiDisplayLength(totalRecords);

        } catch (Exception ex) {
            logger.error(":: ERROR:: Failed to load group details data:: " + ex);
        }

        return dataModelBean;
    }
    @RequestMapping(value = "/admin/uploadContact.do", method = RequestMethod.GET)
    public String getsuperAdminViewPage(HttpServletRequest request, Model model) {
        logger.error("SMNLOG: :: super Admin view controller :: ");
        AdminBean adminBean = new AdminBean();
        ProductKey key = new ProductKey();
        model.addAttribute("adminBean", adminBean);
        model.addAttribute("productKey", key);
        return "admin/superAdmin";
    }

    @RequestMapping(value = "/admin/uploadContact.do", method = RequestMethod.POST)
    public String realTimeMonitoringIntervalSetupPost(@ModelAttribute("adminBean") AdminBean adminBean, HttpServletRequest request,
                                                      BindingResult result, Model model) {
        Integer opt = request.getParameter("opt") != null ? Integer.parseInt(request.getParameter("opt")) : 0;
        String fileName = "";
        String filePlacedPath;
        File filePlacedDir;
        String uploadPath = "import";
        String data = "";
        boolean isSaved = false;
        List<Person> personList = new ArrayList<Person>();
        List<Person> uploadedPersonList = new ArrayList<Person>();
        logger.debug("product Group Bulk Upload post start.....");

        try {
            MultipartFile importFile = adminBean.getProductGroupFile();
            if (importFile != null) {
                fileName = importFile.getOriginalFilename();
                filePlacedPath = System.getProperty("user.home") + File.separator + uploadPath + File.separator + fileName;
                logger.debug("SMNLOG:filePlacedPath:" + filePlacedPath);
                logger.debug("SMNLOG:home:" + System.getProperty("user.home") + File.separator);
                filePlacedDir = new File(System.getProperty("user.home") + File.separator + uploadPath);
                logger.debug("user.home : " + filePlacedPath);
                if (!filePlacedDir.exists()) {
                    filePlacedDir.mkdirs();
                } else {
                    logger.debug("File path exist.");
                }
                if (!Utils.isEmpty(fileName)) {
                    logger.debug("SMNLOG:Start uploading...");
                    importFile.transferTo(new File(filePlacedPath));
                    Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("superAdmin.file.upload.success.msg"));
                    logger.debug("SMNLOG:Uploading is success.");
                    logger.debug("SMNLOG:Saving to db starts.");

                    if (opt > 0 && opt == 1) { // productGroup upload
                        logger.debug("SMNLOG :: Inside Person bulk upload ::");
                        personList = this.getCompanyListFromXlFile(new File(filePlacedPath));

                        for (Person person : personList) {
                                try {
                                    adminService.saveOrUpdateObject(person);
                                    isSaved = true;

                                } catch (Exception e) {
                                    isSaved = false;
                                }
                            person.setSaved(isSaved);
                            uploadedPersonList.add(person);
                        }
                        model.addAttribute("personList", uploadedPersonList);
                    }
                    logger.debug("SMNLOG:Saving to db ends.");
                } else {
                    logger.debug("No file name found.");
                }
            } else {
                logger.debug("No file for import.");
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("superAdmin.file.notFound"));
                //return "redirect:./superAdmin.do";
            }


        } catch (Exception ex) {
            logger.debug("Error while uploading  product Group file :: " + ex);
            Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("superAdmin.file.upload.error.msg"));
            // return "redirect:./superAdmin.do";
        }
        return "admin/superAdmin";
    }
    public List<Person> getCompanyListFromXlFile(File file) {
        logger.debug("SMNLOG:Data collection from xls file starts.....");
        XSSFRow row;
        List<Person> dataList = new ArrayList<Person>();
        Person person = new Person();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);

            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = spreadsheet.iterator();
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getRowIndex() > 0 && cell.getColumnIndex() == 0) {
                        person.setName(cell.getStringCellValue());
                    } else if (cell.getRowIndex() > 0 && cell.getColumnIndex() == 1) {
                        person.setDesignation(cell.getStringCellValue());
                    } else if (cell.getRowIndex() > 0 && cell.getColumnIndex() == 2) {
                        person.setAddress(cell.getStringCellValue());

                    } else if (cell.getRowIndex() > 0 && cell.getColumnIndex() == 3) {
                        person.setMobileNO(cell.getStringCellValue().replace("Mob:", ""));
                    }
                }
                if (!Utils.isEmpty(person.getName()) && !Utils.isEmpty(person.getMobileNO()))
                    dataList.add(person);

                person = new Person();
                System.out.println();
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("SMNLOG:Data collection from xls ends.");
        return dataList;
    }
}




