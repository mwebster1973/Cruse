c_styles={};c_menus={};

c_hideTimeout=300;
c_subShowTimeout=200;
c_keepHighlighted=false;
c_findCURRENT=false;
c_findCURRENTTree=false;
c_overlapControlsInIE=true;
c_rightToLeft=false;

c_imagesPath="";

c_styles['MM']=[
[
// MENU BOX STYLE
1,		// BorderWidth
'solid',	// BorderStyle (CSS valid values except 'none')
'#ffffff',	// BorderColor ('color')
5,		// Padding
'',	// Background 
'',		// IEfilter (NOT currently supported in this beta)
''		// Custom additional CSS for the menu box (valid CSS)
],[
// MENU ITEMS STYLE
0,		// BorderWidth
'solid',	// BorderStyle (CSS valid values except 'none')
'solid',	// OVER BorderStyle
'#ffffff',	// BorderColor ('color')
'#ffffff',	// OVER BorderColor
6,		// Padding
'transparent',	// Background 
'#58245E',	// Over Background 
'#ffffff',	// Color
'#ffff00',	// OVER Color
'0.9em',		// FontSize (values in CSS valid units - %,em,ex,px,pt)
'arial',	// FontFamily
'bold',		// FontWeight (CSS valid values - 'bold','normal','bolder','lighter','100',...,'900')
'none',		// TextDecoration (CSS valid values - 'none','underline','overline','line-through')
'none',		// OVER TextDecoration
'left',		// TextAlign ('left','center','right','justify')
1,		// ItemsSeparatorSize
'solid',	// ItemsSeparatorStyle (border-style valid values)
'#ccccff',	// ItemsSeparatorColor ('color','transparent')
0,		// ItemsSeparatorSpacing
true,			// UseSubMenuImage (true,false)
'[./images/navArrowDown.gif]',	// SubMenuImageSource ('[image_source]')
'[./images/navArrowDown.gif]',	// OverSubMenuImageSource
9,			// SubMenuImageWidth
11,			// SubMenuImageHeight
'8',		// SubMenuImageVAlign ('pixels from item top','middle')
'solid',		// VISITED BorderStyle
'transparent',	// Background 
'transparent',	// Background 
'#ffffff',		// VISITED Color
'none',			// VISITED TextDecoration
'[./images/navArrowDown.gif]',	// VISITED SubMenuImageSource
'solid',		// CURRENT BorderStyle
'transparent',	// Background 
'transparent',	// Background 
'#ffffff',		// CURRENT Color
'none',			// CURRENT TextDecoration
'[./images/navArrowDown.gif]',	// CURRENT SubMenuImageSource
'padding-left:10px;padding-top:5px;padding-right:10px;',		// Custom additional CSS for the items (valid CSS)
'',		// OVER Custom additional CSS for the items (valid CSS)
'',		// CURRENT Custom additional CSS for the items (valid CSS)
''		// VISITED Custom additional CSS for the items (valid CSS)
]];


c_styles['SM']=[
[
// MENU BOX STYLE
1,
'solid',
'#C182D6',
0,
'#C18EDB',
'',
''
],[
// MENU ITEMS STYLE
0,
'solid',
'solid',
'#FEF3BF',
'#C18EDB',
3,
'#C18EDB',
'#58245E',
'#ffffff',
'#ffff00',
'12px',
'sans-serif, Tahoma, Verdana, Geneva, Arial, Helvetica',
'bold',
'none',
'none',
'left',
1,
'solid',
'#C18EDB',
0,
true,
'[./images/navArrowRight.gif]',
'[./images/navArrowRight.gif]',
7,
9,
'6',
'solid',
'transparent',
'transparent',
'#ffffff',
'none',
'[./images/navArrowRight.gif]',
'solid',
'transparent',
'transparent',
'#ffffff',
'none',
'[./images/navArrowRight.gif]',
'',
'',
'',
''
]];


c_menus['Menu1']=[
[
// MAIN-MENU FEATURES
'horizontal',	// ItemsArrangement
'relative',	// Position
'8px',		// X Position
'0',		// Y Position
false,		// RightToLeft display of the sub menus
false,		// BottomToTop display of the sub menus
0,		// X SubMenuOffset
0,		// Y SubMenuOffset
'15em',		// Width
'MM'		// CSS Class
],[
// SUB-MENUS FEATURES
0,		// X SubMenuOffset
1,		// Y SubMenuOffset
'auto',		// Width
'150',		// MinWidth
'500',		// MaxWidth
'SM'		// CSS Class
]];