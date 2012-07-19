<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<c:set var="resPath" value="${pageContext.request.contextPath}/moduleResources/rwandamanuals" />

<h2><spring:message code="rwandamanuals.title" /></h2>

<p><spring:message code="rwandamanuals.shouldProblemsArise" /></p>

<ul>
	<li><a href="${resPath}/hcadminguide.pdf"><spring:message code="rwandamanuals.hcAdminGuide" /></a></li>
	<li><a href="${resPath}/openmrs-guide.pdf"><spring:message code="rwandamanuals.openMRSUserGuide" /></a></li>
</ul>

<p><spring:message code="rwandamanuals.ifAllElseFails" /></p>

<ul>
	<li><spring:message code="rwandamanuals.tollFreeSupportNumber" />: ${supportNumber}</li>
	<li><spring:message code="rwandamanuals.supportEmailAddress" />: <a href="mailto:${supportEmail}">${supportEmail}</a></li>
</ul>

<p><spring:message code="rwandamanuals.moduleManuals" /></p>

<ul>
	<c:forEach items="${manuals}" var="manual">
		<li><a href="${resPath}/modules/${manual.value}">${manual.key}</a></li>
	</c:forEach>	
</ul>

<%@ include file="/WEB-INF/template/footer.jsp"%>

