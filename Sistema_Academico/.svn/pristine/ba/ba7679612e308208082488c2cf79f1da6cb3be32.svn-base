<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %> 
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>

<html>
    <head>
        <meta http-equiv="Expires" content="0" >
        <meta http-equiv="Last-Modified" content="0">
        <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/formulario.css" rel="stylesheet" media="screen"/>
        <title>Mantenimiento de Requisito</title>
        <script type="text/javascript">
            function getRightTop(ref) {
                var position = new Object();
                position.top = 0; //ref.offsetTop;
                position.left =0; // ref.offsetLeft+ref.clientWidth+6;
                return position;
            }
        </script>
    </head>
    <f:view >
        <h:form enctype="multipart/form-data" >
            <%/*
                 * <h:panelGrid columns="2" columnClasses="top,top">
                 * <rich:fileUpload
                 * fileUploadListener="#{fileUploadBean.listener}"
                 * maxFilesQuantity="#{fileUploadBean.uploadsAvailable}"
                 * id="upload" acceptedTypes="jpg, gif, png, bmp, pdf" >
                 * <a4j:support event="onuploadcomplete" reRender="info" />
                 * </rich:fileUpload> <h:panelGroup id="info"> <rich:panel
                 * bodyClass="info"> <f:facet name="header"> <h:outputText
                 * value="Uploaded Files Info" /> </f:facet> <h:outputText
                 * value="No files currently uploaded"
                 * rendered="#{fileUploadBean.size==0}" /> <rich:dataGrid
                 * columns="1" value="#{fileUploadBean.files}" var="file"
                 * rowKeyVar="row"> <rich:panel
                 * bodyClass="rich-laguna-panel-no-header"> <h:panelGrid
                 * columns="2"> <a4j:mediaOutput element="img"
                 * mimeType="#{file.mime}"
                 * createContent="#{fileUploadBean.paint}" value="#{row}"
                 * style="width:100px; height:100px;" cacheable="false">
                 * <f:param value="#{fileUploadBean.timeStamp}" name="time"/>
                 * </a4j:mediaOutput> <h:panelGrid columns="2"> <h:outputText
                 * value="File Name:" /> <h:outputText value="#{file.name}"
                 * /> <h:outputText value="File Length(bytes):" />
                 * <h:outputText value="#{file.length}" /> </h:panelGrid>
                 * </h:panelGrid> </rich:panel> </rich:dataGrid>
                 * </rich:panel> <rich:spacer height="3"/> <br />
                 * <a4j:commandButton
                 * action="#{fileUploadBean.clearUploadData}" reRender="info,
                 * upload" value="Clear Uploaded Data"
                 * rendered="#{fileUploadBean.size>0}" /> </h:panelGroup>
             </h:panelGrid>
                    */%>
            <rich:tree style="width:300px" nodeSelectListener="#{fileUploadBean.seleccionarElemento}"
                       reRender="masterDetalle, masterTable, masterScroll" ajaxSubmitSelection="true"  switchType="client"
                       value="#{fileUploadBean.treeNode}" var="item"  >
                <rich:treeNode>
                    <h:outputText value="#{item}" escape="false"/>
                </rich:treeNode>
            </rich:tree>

        </h:form>
    </f:view>
</html>