import axios from 'axios';
import { Component, registerComponent } from 'js/component';
import {
    addEventToDataLayer,
    setCsrfToken,
    isEditMode,
    getCookie,
    detectBrand
} from 'js/helpers';

const componentName = 'cmp-linkedin-post';

class LinkedInPost extends Component {

    addEventListeners() {
        this.elements.submitButton.addEventListener('click', async (e) => {
            e.preventDefault();
            if (!this.hasClicked) {
                try {
                    this.hasClicked = true;
                    const formData = new FormData(this.elements.form);
                    formData.delete(':formstart');
                    formData.delete(':redirect');
                    const { data } = await axios.post(this.updatePreferencesPath, formData, {timeout: 30000});
                    if (data.success === true) {
                        try {
                            const preferencesNotification = this.enableMobileSurvey === 'true' ? 'mobile-experience-survey' : 'tobacco-preferences-notification';
                            await axios.post(this.actionLogServlet,
                                `dbAction=add&action=${preferencesNotification}&resourcePath=${this.currentPage}&additionalData=done`);
                            this.elements.content.classList.add('hide');
                            this.elements.thankYou.classList.remove('hide');
                        } catch(error) {
                            addEventToDataLayer('error', {code: 51003, message: error.message});
                        }
                    }
                } catch(error) {
                    addEventToDataLayer('error', {code: 51002, message: error.message});
                }
            }
        });
    }
    
    async init() {
        this.elements = {
            body: document.querySelector('body'),
            content: this.element.querySelector(`.${componentName}__content`),
            submitButton: this.element.querySelector(`.${componentName}__submit`),
            footer: document.body.querySelector('.footer'),
            mask: this.element.querySelector(`.${componentName}__mask`),
            welcome: this.element.querySelector(`.${componentName}__welcome`),
        };
        this.hasClicked = false;
        this.actionLogServlet = this.element.dataset.actionLogServlet;
        this.currentPage = this.element.dataset.currentPage;
        this.resourcePath = this.element.dataset.resourcePath;
        this.enableImageUpload = this.element.dataset.enableImageUpload;
        this.templateSelection = this.element.dataset.templateSelection;

        if (document.readyState !== 'loading') {
            setCsrfToken(this.element);
        } else {
            window.addEventListener('DOMContentLoaded', () => {
                setCsrfToken(this.element);
            });
        }
        if(this.elements.submitButton) {
            this.elements.submitButton.disabled = true;
        }
        this.addEventListeners();
        this.cloneFooterIntoMask();
    }
}

registerComponent(`.${componentName}`, LinkedInPost);
