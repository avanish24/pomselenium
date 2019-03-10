package com.qa.pages;

public class OR {
	// Common Controls
	public static final String sForm_id = "//Form[@id='";

	// PageFactory OR ---> For LOGIN Page
	public static final String sPortalLoginOptions = "//*[@id='top-login-btn']";
	public static final String sInvalidLoginTxt = "//*[@id='login-form-failed-reason']/div";
	public static final String sPortalEmailInput = "//*[@id='login-form-username']";
	public static final String sPortalPasswordInput = "//*[@id='login-form-password']";
	public static final String sPortalLoginBtn = "//*[@type='submit' and contains(text(),'Login')]";
	public static final String sPasswordPage = "//*[@class='login-panel']/descendant::*[@type='password']";

	// PageFactory OR ---> For Controls available on all pages
	public static final String sDownArrowMenu = "//div[@id='top-nav']/div/button"; // logged in user profile
	public static final String sLogoutBtn = "//*[@id='top-logout-btn']";
	public static final String sGlobalNavItem = "//*[contains(@class, 'global-nav')]/descendant::*[contains(@class, 'ki-nav-item')]";
	public static final String sGlobalNavTooltip = "./descendant::div[@class='tooltip']";
	public static final String sSidebarMenuTooltip = "//section[@class='context-sidebar-menu']/descendant::div[@class='tooltip']";
	public static final String sSidebarMenuIcon = "./preceding-sibling::*[contains(@class, 'ki-sidebar-icon')]";
	public static final String sTabsInFlyout = "//*[contains(@class,'KiTabs-theme__tab___1Pvx' )]";

	public static final String sMainNav = "//*[@class='nav-container']"; // Panel on the left with main menu options
	public static final String sHelpLink = "//*[@class='ki-nav-item help']";
	public static final String sPrimaryNavBtn = "//*[@class='bottom-arrow']";
	public static final String sPlusIcon = "//*[text()='add_circle']/parent::button";

	public static final String sBreadcrumbBar = "//*[contains(@class, 'kiAppBar-theme__kiAppBar')]";
	public static final String sDashboardsLink = "//a[contains(@href,'/dashboards')]";
	public static final String sDatasetsLink = "//a[contains(@href,'/datasets')]";
	public static final String sDebtLink = "//a[contains(@href,'/debt')]";
	public static final String sReportLink = "//a[contains(@href,'/reports')]";
	public static final String sFundingLink = "//a[contains(@href,'/fundingOptimization')]";
	public static final String sBusinessFuncLink = "//a[contains(@href,'/businessFunctions')]";
	public static final String sAuditLink = "//a[contains(@href,'/audit')]";
	public static final String sMaintenanceLink = "//*[@class='ki-nav-item ']/descendant::*[contains(text(),'MAINTENANCE')]";
	public static final String sSubmissionLink = "//*[contains(@href,'submissions')]";
	public static final String sFundingVehiclesLink = "//*[contains(@href,'fundingVehicles')]";
	public static final String sMaintenanceIcon = "//*[contains(text(),'MAINTENANCE')]/parent::div[@class='top-level-menu']";

	// PageFactory OR ---> For DASHBOARDS Page
	public static final String sDashboardPageLbl = "//*[@class='deskpro-agent-top-bar']";

	// PageFactory OR ---> For DATASETS Page
	public static final String sDatasetSrchTxtBx = "//input[@class='inputElement']";
	public static final String sDatasetList = "//ul[@class='kiList-theme__kiList___oD8q0 ki-panel']/li";
	public static final String sDatasetName = "//div[@class='dataset-title']";
	public static final String sDatasetExploreLink = "//p[text()='Summary / Asset / Time Series']";

	// PageFactory OR ---> For FV Page
	public static final String sSearchFVName = "//input[@name='fundingVehiclesNameFilter']";
	public static final String sExploreFVName1 = "//td[text()='";
	public static final String sExploreFVName2 = "']/preceding::a[text()='";
	public static final String sExploreFVName3 = "']";
	public static final String sFV_Menu_DD = "(//*[contains(@class,'css-10nd86i')]/descendant::*[contains(@class,'css-1ep9fjw')]/ancestor::*[contains(@class,'css-1wy0on6')])[1]";
	// PageFactory OR ---> For FV creation from FV Page
	public static final String sFVSetupHeaderLbl = "//header[text()='Create Funding Vehicle']";
	public static final String sFVSetup_Name = "//*[text()='Name']/parent::*[@class='ki-input ']/input";
	public static final String sFVSetup_DatasetDD = "//span[text()='Dataset:']/parent::*/descendant::*[contains(@class,'aut-select__dropdown-indicator')]";
	public static final String sFVSetup_PoolsDD = "//span[text()='Pools:']/parent::*/descendant::*[contains(@class,'aut-select__dropdown-indicator')]";
	public static final String sFVSetup_CopyDD = "//span[text()='Copy:']/parent::*/descendant::*[contains(@class,'aut-select__dropdown-indicator')]";
	public static final String sFVSetup_SaveBtn = "//footer/button[text()='Save']";
	public static final String sFVSetup_CancelBtn = "//footer/button[text()='Cancel']";
	public static final String sFVSetup_ValidationMsg = "//*[contains(@class,'kiSnackbar')]/span";
	public static final String sFVSetup_LegalChkBx = "//*[@class='check-boxes']/label/span[text()='Legal']";
	public static final String sFVSetup_CalendarChkBx = "//*[@class='check-boxes']/label/span[text()='Calendar']";
	public static final String sFVSetup_ConstraintsChkBx = "//*[@class='check-boxes']/label/span[text()='Constraints & Convenants']";

	// PageFactory OR ---> Dates for FV
	public static final String sCalendarDD = "//div[contains(@class, 'fvm-calendar-selector')]/descendant::*[name()='svg']";
	public static final String sExistingCalType1 = "//div[text()='";
	public static final String sExistingCalType2 = "']/ancestor::*[contains(@name,'calendar-form')]";
	public static final String sDeleteBtn1 = "//div[text()='";
	public static final String sDeleteBtn2 = "']/ancestor::form/descendant::button/i[text()='delete']/parent::button";
	public static final String sNewCalTypeDDIcon = "//*[contains(@id,'calendar-form-new')]/descendant::*[name()='svg']";
	public static final String sHasParent2 = "']/descendant::*[contains(@class,'useCalendarChk')]"; // sForm_Id + <form
																									// id> + sHasParent2
	public static final String sDateBasisDDIcon = "//*[contains(@id,'calendar-form-new')]/descendant::*[text()='Date Basis']//ancestor::*[contains(@class,'calendarTypeSelect')]/descendant::*[name()='svg'][2]";
	public static final String sStartDate2 = "']/descendant::input[@name='startDate']"; // sForm_Id + <form id> +
																						// sStartDate2
	public static final String sRecurrence2 = "']/descendant::*[contains(@class,'recurrenceSelect')]/descendant::*[name()='svg']"; // sForm_Id
																																	// +
																																	// <form
																																	// id>
																																	// +
																																	// sRecurrence2
	public static final String sRange2 = "']/descendant::*[contains(@class,'rangeSelect')]/descendant::*[name()='svg']"; // sForm_Id
																															// +
																															// <form
																															// id>
																															// +
																															// sRange2
	public static final String sEndDate2 = "']/descendant::input[@name='endDate']"; // sForm_Id + <form id> + sEndDate2
	public static final String sOccurrences2 = "']/descendant::input[@name='occurrences']"; // sForm_Id + <form id> +
																							// sOccurrences2
	public static final String sOffset2 = "']/descendant::input[@name='offset']"; // sForm_Id + <form id> + sOffset2
	public static final String sAddBtn2 = "']/descendant::*[text()='add']/ancestor::button[contains(@class,'button')]"; // sForm_Id
																														// +
																														// <form
																														// id>
																														// +
																														// sAddBtn2
	public static final String sSaveBtn2 = "']/descendant::*[text()='save']/ancestor::button[contains(@class,'button')]"; // sForm_Id
																															// +
																															// <form
																															// id>
																															// +
																															// sAddBtn2
	public static final String sDatePicker1 = "//*[text()='";
	public static final String sDatePicker2 = "']/parent::*/descendant::*[contains(@type,'text')]";

	// PageFactory OR ---> Delete Dates
	public static final String sDelMsgDelBtn = "//li/ancestor::*[@aria-label='Delete Selected Settings']/descendant::button[text()='Delete']";

	// To Select the Values from any drop downs
	public static final String sSelectDropDownValues1 = "//div[@role='option' and text()='";
	public static final String sSelectDropDownValues2 = "']";

	// Constraints
	public static final String sFVSelectDropdownMenu = "(//*[contains(@class,'css-10nd86i')]/descendant::*[contains(@class,'css-1ep9fjw')]/ancestor::*[contains(@class,'css-1wy0on6')])[1]";
	public static final String sFV_Cons_SelectionDD = "//*[text()='Selection']/parent::*/descendant::*[contains(@class,'aut-select__dropdown-indicator')]";
	public static final String sFV_Cons_NoConstraintLbl = "//*[@class='KiTwoPanelList-theme__listHeader___3V1uo']";
	public static final String sFV_Cons_AddConstraintLbl = "//div[text()='Add Constraint']";
	public static final String sFV_Cons_EditConstraintLbl = "//div[text()='Edit Constraint']";
	public static final String sFV_Cons_TypeDD = "//*[text()='Type']/parent::*/descendant::*[contains(@class,'aut-select__dropdown-indicator')]";
	public static final String sFV_Cons_NameTxtBx = "//*[text()='Name']/parent::*/descendant::*[contains(@class,'inputElement')]";
	public static final String sFV_Cons_DataColumnDD = "//*[text()='Data Column']/parent::*/descendant::*[contains(@class,'aut-select__dropdown-indicator')]";
	public static final String sFV_Cons_LogicDD = "//*[text()='Logic']/parent::*/descendant::*[contains(@class,'aut-select__dropdown-indicator')]";
	public static final String sFV_Cons_TargetValueTxtBx = "//*[contains(text(),'Target Value')]/ancestor::div[contains(@class,'ki-input')]/input";
	public static final String sFV_Cons_AddConstraintBtn = "//*[contains(@class, 'qa_ktpl_add')]";
	public static final String sFV_Cons_SaveConstraintBtn = "//*[text()='save']/parent::button[contains(@type,'button')]";
	public static final String sFV_Cons_UndoConstraintBtn = "//*[text()='undo']/parent::button[contains(@type,'button')]";
	public static final String sFV_Cons_DelConstraintBtn = "//*[text()='delete']/parent::button[contains(@type,'button')]";
	public static final String sFV_Cons_NameMustBeUniqueLbl = "//*[@class='error' and text()='Name must be unique']";
	public static final String sFV_Cons_NameErr = "//*[text()='Name']/parent::div";
	public static final String sFV_Cons_ActiveListItem = "//*[contains(text(),'${variable}')]/parent::*[contains(@class, 'activeListItem')]";
	public static final String sFV_Cons_ExistingCons = "//*[contains(text(),'${variable}')]/parent::*[contains(@class, 'KiTwoPanelList-theme__listItem')]";
	// specific to Balance Limit Constraint
	public static final String sFV_Cons_ExcludeZeroChkBx = "//*[contains(text(),'Exclude zero balance')]/parent::*/descendant::*[contains(@class,'KiCheckbox-theme__check')]";
	// specific to Exclusion Constraint
	public static final String sFV_Cons_ConstraintTypeDD = "//*[text()='Constraint Type']/parent::*/descendant::*[contains(@class,'aut-select__dropdown-indicator')]";
	// specific to Concentration Constraint
	public static final String sFV_Cons_MinTxtBx = "//*[text()='Min']/ancestor::div[contains(@class,'ki-input')]/input";
	public static final String sFV_Cons_MaxTxtBx = "//*[text()='Max']/ancestor::div[contains(@class,'ki-input')]/input";
	public static final String sFV_Cons_MinPerTxtBx = "//*[text()='Min%']/ancestor::div[contains(@class,'ki-input')]/input";
	public static final String sFV_Cons_MaxPerTxtBx = "//*[text()='Max%']/ancestor::div[contains(@class,'ki-input')]/input";
	public static final String sFV_Cons_IncludePlcHldr = "//*[text()='Include']/parent::*/descendant::*[contains(@class,'aut-select__placeholder')]";
	public static final String sFV_Cons_ExcludePlcHldr = "//*[text()='Exclude']/parent::*/descendant::*[contains(@class,'aut-select__placeholder')]";
	public static final String sFV_Cons_IncludeTxtBx = "//*[text()='Include']/parent::*/descendant::*[contains(@class,'aut-select__value-container')]//input";
	public static final String sFV_Cons_ExcludeTxtBx = "//*[text()='Exclude']/parent::*/descendant::*[contains(@class,'aut-select__value-container')]//input";
	public static final String sFV_Cons_Value = "//*[text()='Value']/parent::*/descendant::*[contains(@class,'inputElement')]";
	public static final String sFV_Cons_WeightedByDD = "//*[text()='Weighted By']/parent::*/descendant::*[contains(@class,'aut-select__dropdown-indicator')]";
	// specific to Weighted Average Constraint

	// PageFactory OR ---> For SUBMISSIONS Page
	public static final String sSubmissionsLink = "";
	public static final String sSubmissionsPageAddBtn = "//*[@title='Click to create a new submission.']";
	public static final String sDatasetNameTxtBx = "//*[text()='Create or Choose a Dataset']/following::*[contains(@class,'category-name-selector')]/descendant::*[@class='aut-select__input']/input";
	public static final String sDatasetNameDropDown = "//*[text()='Create or Choose a Dataset']/following::*[contains(@class,'aut-select__menu')]/descendant::*[contains(@class,'aut-select__option--is-focused')]";
	public static final String sSelectSnapshotDropdown = "//*[text()='Snapshot Date']/parent::*/descendant::*[contains(@type,'text')]";
	public static final String sSnapshotDatePicker_PreviousYearBtn = "//div[@class='react-datepicker']/button[@class='react-datepicker__navigation react-datepicker__navigation--previous']";
	public static final String sSnapshotDatePicker_ForwardYearBtn = "//div[@class='react-datepicker']/button[@class='react-datepicker__navigation react-datepicker__navigation--next']";
	public static final String sSnapshotDatePicker_CurrentMonth = "//div[@class='react-datepicker']/descendant::*[contains(@class,'react-datepicker__month-container')]/descendant::*[@class='react-datepicker__current-month']";
	public static final String sSnapshotDatePicker_Day = "//div[@class='react-datepicker__month']/descendant::*[contains(@class,'react-datepicker__day') and not(contains(@class,'react-datepicker__day--outside-month')) and @aria-label='day-";
	public static final String sSelectDelimiterDropDown = "//*[text()='Delimiter']/ancestor::*[contains(@class,'submission-delimiter')]/descendant::*[contains(@class,'aut-select__indicators')]";
	public static final String sSelectFilesBtn = "//input[@type='file'][@class='react-fine-uploader-file-input']";
	public static final String sSelectDelimiter1 = "//*[contains(text(),'Delimiter')]/parent::*/descendant::*[contains(text(),'";
	public static final String sSelectDelimiter2 = "')]";
	public static final String sFileNameFromSelectFilesBox = "";
	public static final String sUploadBtn = "//*[text()='Start Upload']";
	public static final String sMapDropDownMenu1 = "(//*[contains(@class,'schema-column-list')]/descendant::*[contains(@value,'";
	public static final String sMapDropDownMenu1End = "')]/parent::*/parent::*/descendant::*[contains(@class,'aut-select__control')])[1]";
	public static final String sMapDropDownMenu2End = "')]/parent::*/parent::*/descendant::*[contains(@class,'aut-select__control')])[2]";
	public static final String sSelectDropdownValue1 = "//*[contains(@class,'aut-select__option') and text()='";
	public static final String sSelectDropdownValue2 = "']";
	public static final String sAssetIDColDropDown = "";
	public static final String sBalanceColDropDown = "";
	public static final String sUploadProgress = "//*[@class='submission-message-progress']";
	public static final String sSubValidateBtn = "//*[text()='validate' and @type='button']";
	public static final String sSubmissionResultsMssg = "//article[@class='schema-results-list']";
	public static final String sGreenChip = "//div[contains(@class,'KiChip-theme__chip')]/descendant::*[contains(@class,'KiChip-theme__avatar')]/descendant::*[@class='material-icons' and text()=\"check\"]";
	public static final String sActivityStatus = "//div[contains(@class,'KiChip-theme__chip')]/span[text() =\"Validate\"]";
	public static final String sSubCommitBtn = "//*[text()='Commit' and @type='button']";
	public static final String sSubPageDatasetNameLocator = "//*[@class='submission-upload-header-content']/span[1]";
	public static final String sCommitProgressEnd = "')]/parent::*/following::*[@class='submission-message-progress']";
	public static final String sCommitSuccess1 = "//*[contains(text(),'";
	public static final String sCommitSuccess2 = "')]/parent::*/following-sibling::*[@class='submission-stages']/descendant::*[text()='check']";
	public static final String sCommitActivityStatus2 = "')]/parent::*/following-sibling::*[@class='submission-stages']/descendant::*[text()='check']";
	public static final String sInvalidFileExtensionMsg = "//*[text()='AutoInvalidFileFormat.docx has an invalid extension. Valid extension(s): csv, txt.']";
	public static final String sSelectedFilesIcon = "//*[@class='react-fine-uploader-thumbnail-container react-fine-uploader-gallery-thumbnail-container']";
	public static final String sSubInvalidDelimiterErroMsg = "//*[contains(@class,'upload-error-panel') and contains(text(),'500 - \"File is not a valid comma delimited file\"')]";
	public static final String sSubSpecialCharsErrorMsg = "//*[contains(text(),'contains special characters or spaces')]";
	public static final String sSubDuplicateLoanErroMsg = "//*[@class='submission-message-error' and contains(text(),'Commit Failed: 500 - \"java.lang.Exception: Account is not unique in the submitted file')]";
	public static final String sSubDuplicateColsErrorMsg = "//*[contains(text(),'500 - \"File contains duplicate column Names') and @class='upload-error-panel ']";

	// Navigations - FV
	public static final String oAppBar = "//*[contains(@class,'kiAppBar')]";
	public static final String oAppBarHeaders = "//h1";
	public static final String oMaintenanceButton = "//*[@class='ki-nav-item ']/descendant::*[contains(text(),'MAINTENANCE')]/parent::*";
	public static final String oFVTable = "//table[@class='funding-vehicle-table']";
	public static final String oFVTableRow = "//table[@class='funding-vehicle-table']/tbody/tr";
	public static final String oFVTableFVName1 = "[";
	public static final String oFVTableFVName2 = "]/td[2]/div/h5/a";
	public static final String oFVDatasetName1 = "[";
	public static final String oFVDatasetName2 = "]/td[4]";

	// Historical Columns
	public static final String sDataset_Calc_Hist_Add_Column = "//*[text()='Add Columns']";
	public static final String sDataset_Calc_Hist_Column_DD = "//*[text()='Columns']/parent::*/descendant::*[text()='Select Columns...']";
	public static final String sDataset_Calc_Hist_Generate_Btn = "//*[text()='Generate' and @type='button']";

}
