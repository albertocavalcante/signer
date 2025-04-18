/*
 * Demoiselle Framework
 * Copyright (C) 2021 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 *
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 *
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */

package org.demoiselle.signer.timestamp.configuration;

import org.demoiselle.signer.core.util.MessagesBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TimeOut configurations.
 *
 * @author emerson.saito@gmail.com
 */
public class TimeStampConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeStampConfig.class);
	private static MessagesBundle timeStampMessagesBundle = new MessagesBundle();

	/**
	 * System key to set timeout for timestamp connector
	 */
	public static final String TIMESTAMP_TIMEOUT = "signer.timestamp.timeout";

	/**
	 * System environment key to set timeout for timestamp connector
	 */
	public static final String ENV_TIMESTAMP_TIMEOUT = "SIGNER_TIMESTAMP_TIMEOUT";

	/**
	 * System key to set how many times replay timestamp connector
	 */
	public static final String TIMESTAMP_CONNECT_REPLAY = "signer.timestamp.connect_replay";

	/**
	 * System environment key to set how many times replay timestamp connector
	 */
	public static final String ENV_TIMESTAMP_CONNECT_REPLAY = "SIGNER_TIMESTAMP_CONNECT_REPLAY";

	/**
	 * System environment key to set read time out for timestamp connector
	 */
	public static final String ENV_TIMESTAMP_READ_TIMEOUT = "SIGNER_TIMESTAMP_READ_TIMEOUT";

	/**
	 * System key to set read time out for timestamp connector
	 */
	public static final String TIMESTAMP_READ_TIMEOUT = "signer.timestamp.read.timeout";

	/**
	 * System environment key to set CLIENT_CREDENTIALS for timestamp API connector by SERPRO
	 */
	public static final String ENV_TIMESTAMP_CLIENT_CREDENTIALS = "SIGNER_TIMESTAMP_CLIENT_CREDENTIALS";

	/**
	 * System key to set CLIENT_CREDENTIALS for timestamp API connector by SERPRO
	 */
	public static final String TIMESTAMP_CLIENT_CREDENTIALS = "signer.timestamp.client.credentials";
		
	/**
	 * System environment key to set use (true) the  API Connector by SERPRO
	 */
	public static final String ENV_TIMESTAMP_API_SERPRO = "SIGNER_TIMESTAMP_API_SERPRO";

	/**
	 * System key to set use (true) the API Connector by SERPRO
	 */
	public static final String TIMESTAMP_API_SERPRO = "signer.timestamp.api.serpro";

	public static TimeStampConfig instance = new TimeStampConfig();

	// default is 30 seconds
	private int timeOut = 30000;

	private int connectReplay = 3;

	private int readTimeOut = 10000;

	private String clientCredentials = "";
	
	private boolean apiSERPRO = false;

	public static TimeStampConfig getInstance() {
		if (instance == null) {
			instance = new TimeStampConfig();
		}
		return instance;
	}

	private TimeStampConfig() {
		try {
			String varTimeOut = System.getenv(ENV_TIMESTAMP_TIMEOUT);
			if (varTimeOut == null || varTimeOut.isEmpty()) {
				varTimeOut = (String) System.getProperties().get(TIMESTAMP_TIMEOUT);
				if (varTimeOut == null || varTimeOut.isEmpty()) {
					LOGGER.debug("DEFAULT");
					LOGGER.debug(timeStampMessagesBundle.getString("info.timestamp.timeout.value", getTimeOut()));
				} else {
					LOGGER.debug("key");
					setTimeOut(Integer.valueOf(varTimeOut));
				}
			} else {
				LOGGER.debug("ENV");
				setTimeOut(Integer.valueOf(varTimeOut));
			}
		} catch (Exception e) {
			LOGGER.debug(timeStampMessagesBundle.getString("info.timestamp.timeout.value", getTimeOut()));

		}
		try {
			String varConnectReplay = System.getenv(ENV_TIMESTAMP_CONNECT_REPLAY);
			if (varConnectReplay == null || varConnectReplay.isEmpty()) {
				varConnectReplay = (String) System.getProperties().get(TIMESTAMP_CONNECT_REPLAY);
				if (varConnectReplay == null || varConnectReplay.isEmpty()) {
					LOGGER.debug("DEFAULT");
					LOGGER.debug(timeStampMessagesBundle.getString("info.timestamp.connect.replay.value",
							getConnectReplay()));
				} else {
					LOGGER.debug("key");
					setConnectReplay(Integer.valueOf(varConnectReplay));
				}
			} else {
				LOGGER.debug("ENV");
				setConnectReplay(Integer.valueOf(varConnectReplay));
			}
		} catch (Exception e) {
			LOGGER.debug(timeStampMessagesBundle.getString("info.timestamp.connect.replay.value", getConnectReplay()));

		}
		try {
			String varReadTimeOut = System.getenv(ENV_TIMESTAMP_READ_TIMEOUT);
			if (varReadTimeOut == null || varReadTimeOut.isEmpty()) {
				varReadTimeOut = (String) System.getProperties().get(TIMESTAMP_READ_TIMEOUT);
				if (varReadTimeOut == null || varReadTimeOut.isEmpty()) {
					LOGGER.debug("DEFAULT");
					LOGGER.debug(
							timeStampMessagesBundle.getString("info.timestamp.read.timeout.value", getReadTimeOut()));
				} else {
					LOGGER.debug("key");
					setReadTimeOut(Integer.valueOf(varReadTimeOut));
				}
			} else {
				LOGGER.debug("ENV");
				setReadTimeOut(Integer.valueOf(varReadTimeOut));
			}
		} catch (Exception e) {
			LOGGER.debug(timeStampMessagesBundle.getString("info.timestamp.read.timeout.value", getReadTimeOut()));
		}
		try {
			String varClientCredentials = System.getenv(ENV_TIMESTAMP_CLIENT_CREDENTIALS);
			if (varClientCredentials == null || varClientCredentials.isEmpty()) {
				varClientCredentials = (String) System.getProperties().get(TIMESTAMP_CLIENT_CREDENTIALS);
				if (varClientCredentials == null || varClientCredentials.isEmpty()) {
					LOGGER.debug("DEFAULT");
					LOGGER.debug(
							timeStampMessagesBundle.getString("info.timestamp.client.credentials.value", getClientCredentials()));
				} else {
					LOGGER.debug("key");
					setClientCredentials(varClientCredentials);
				}
			} else {
				LOGGER.debug("ENV");
				setClientCredentials(varClientCredentials);
			}
		} catch (Exception e) {
			LOGGER.debug(timeStampMessagesBundle.getString("info.timestamp.client.credentials.value", getClientCredentials()));
		}
		try {
			String varApiSERPRO = System.getenv(ENV_TIMESTAMP_API_SERPRO);
			if (varApiSERPRO == null || varApiSERPRO.isEmpty()) {
				varApiSERPRO = (String) System.getProperties().get(TIMESTAMP_API_SERPRO);
				if (varApiSERPRO == null || varApiSERPRO.isEmpty()) {
					LOGGER.debug("DEFAULT");
					LOGGER.debug(timeStampMessagesBundle.getString("info.timestamp.api.serpro.value", isApiSERPRO()));
				} else {
					LOGGER.debug("key");
					if (varApiSERPRO.equalsIgnoreCase("true")) {
						setApiSERPRO(true);
					}else {
						setApiSERPRO(false);
					}					
				}
			} else {
				LOGGER.debug("ENV");
				if (varApiSERPRO.equalsIgnoreCase("true")) {
					setApiSERPRO(true);
				}else {
					setApiSERPRO(false);
				}
			}
		} catch (Exception e) {
			LOGGER.debug(timeStampMessagesBundle.getString("info.timestamp.api.serpro.value", isApiSERPRO()));

		}
	}

	/**
	 * @return the timeout.
	 */
	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int parmTimeOut) {
		this.timeOut = parmTimeOut;
	}
	/**
	 * 
	 * @return the connectReplay.
	 */
	public int getConnectReplay() {
		return connectReplay;
	}

	public void setConnectReplay(int connectReplay) {
		this.connectReplay = connectReplay;
	}

	/**
	 * 
	 * @return the readTimeOut.
	 */
	public int getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	/**
	 * 
	 * @return the clientCredentials.
	 */
	public String getClientCredentials() {
		return clientCredentials;
	}

	/**
	 * Para utilização no parâmetro Authorization, é necessário concatenar os códigos Consumer Key e Consumer Secret
	 * separados pelo caracter ":", e converter o resultado em BASE64. 
	 * No exemplo a seguir, é retornada a string ZGphUjIx[...]IzT3RlCg:
     * echo -n "djaR21PGoYp1iyK2n2ACOH9REdUb:ObRsAJWOL4fv2Tp27D1vd8fB3Ote" | base64
	 * 
	 * @param clientCredentials a string
	 */
	public void setClientCredentials(String clientCredentials) {
		this.clientCredentials = clientCredentials;
	}

	public boolean isApiSERPRO() {
		return apiSERPRO;
	}

	public void setApiSERPRO(boolean apiSERPRO) {
		this.apiSERPRO = apiSERPRO;
	}
}
