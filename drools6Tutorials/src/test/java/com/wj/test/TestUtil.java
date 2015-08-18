package com.wj.test;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.StatelessKieSession;

/**
 * @Title: TestUtil.java
 * @Package
 * @Description: ���Թ����࣬�ṩ������״̬/��״̬session��container���ͷ���Դ�ȷ���
 * @author wujiang
 * @version V1.0
 */
public class TestUtil {

	
	//����һ����״̬session
	static StatelessKieSession createStatelessKieSession(String... drlResourcesPaths) {

		KieServices kServices = KieServices.Factory.get();
		KieContainer kcontainer = createKieContainer(kServices, drlResourcesPaths);

		// Configure and create the KieBase
		// ����kiebase
		KieBaseConfiguration kbconf = kServices.newKieBaseConfiguration();
		KieBase kbase = kcontainer.newKieBase(kbconf);

		// Configure and create the KieSession
		KieSessionConfiguration ksconf = kServices.newKieSessionConfiguration();

		return kbase.newStatelessKieSession(ksconf);
	}

	//���������������״̬session
	private static KieContainer createKieContainer(KieServices ks, String[] drlResourcesPaths) {

		// Create the in-memory File System and add the resources files to it
		KieFileSystem kfs = ks.newKieFileSystem();
		KieResources kieResources = ks.getResources();

		for (String path : drlResourcesPaths) {
			kfs.write(kieResources.newClassPathResource(path));
		}

		// Create the builder for the resources of the File System
		KieBuilder kbuilder = ks.newKieBuilder(kfs);

		// Build the Kie Bases
		//����base
		kbuilder.buildAll();

		// Check for errors
		//�������еĴ���
		if (kbuilder.getResults().hasMessages(Level.ERROR)) {
			throw new IllegalArgumentException(kbuilder.getResults().toString());
		}

		// Get the Release ID (mvn style: groupId, artifactId,version)
		ReleaseId relId = kbuilder.getKieModule().getReleaseId();
		// Create the Container, wrapping the KieModule with the given ReleaseId
		return ks.newKieContainer(relId);
	}

	// ������״̬ session
	public static KieSession createKieSession(String... drlResourcesPaths) {

		KieServices ks = KieServices.Factory.get();
		KieContainer kcontainer = createKieContainer(ks, drlResourcesPaths);

		// Configure and create the KieBase
		KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
		KieBase kbase = kcontainer.newKieBase(kbconf);

		// Configure and create the KieSession
		KieSessionConfiguration ksconf = ks.newKieSessionConfiguration();
		return kbase.newKieSession(ksconf, null);
	}

	// �ͷ���Դ
	public static void dispose(KieSession session) {
		if (session != null) {
			session.dispose();
		}
	}
}
