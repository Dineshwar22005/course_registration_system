document.addEventListener('DOMContentLoaded', () => {
  const header = document.getElementById('header');
  const navToggle = document.getElementById('nav-toggle');
  const navMenu = document.getElementById('nav-menu');
  const navLinks = document.querySelectorAll('.nav__link');
  const sections = document.querySelectorAll('.section');
  const contactForm = document.getElementById('contact-form');
  const formStatus = document.getElementById('form-status');

  // Sticky header on scroll
  window.addEventListener('scroll', () => {
    header.classList.toggle('scrolled', window.scrollY > 50);
    updateActiveNav();
  });

  // Mobile navigation toggle
  navToggle.addEventListener('click', () => {
    navToggle.classList.toggle('active');
    navMenu.classList.toggle('open');
    document.body.style.overflow = navMenu.classList.contains('open') ? 'hidden' : '';
  });

  // Close mobile menu on link click
  navLinks.forEach(link => {
    link.addEventListener('click', () => {
      navToggle.classList.remove('active');
      navMenu.classList.remove('open');
      document.body.style.overflow = '';
    });
  });

  // Active nav link based on scroll position
  function updateActiveNav() {
    const scrollPos = window.scrollY + header.offsetHeight + 100;

    sections.forEach(section => {
      const top = section.offsetTop;
      const height = section.offsetHeight;
      const id = section.getAttribute('id');

      if (scrollPos >= top && scrollPos < top + height) {
        navLinks.forEach(link => {
          link.classList.toggle('active', link.getAttribute('href') === `#${id}`);
        });
      }
    });
  }

  // Scroll reveal animations
  const fadeElements = document.querySelectorAll(
    '.section__header, .about__grid, .skill-card, .skill-bar, .project-card, .contact__grid'
  );

  fadeElements.forEach(el => el.classList.add('fade-in'));

  const observer = new IntersectionObserver(
    entries => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          entry.target.classList.add('visible');
          observer.unobserve(entry.target);
        }
      });
    },
    { threshold: 0.15, rootMargin: '0px 0px -50px 0px' }
  );

  fadeElements.forEach(el => observer.observe(el));

  // Animate skill bars when visible
  const skillBars = document.querySelectorAll('.skill-bar__fill');

  const barObserver = new IntersectionObserver(
    entries => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          const fill = entry.target;
          const width = fill.getAttribute('data-width');
          fill.style.setProperty('--fill-width', `${width}%`);
          fill.classList.add('animated');
          barObserver.unobserve(fill);
        }
      });
    },
    { threshold: 0.5 }
  );

  skillBars.forEach(bar => barObserver.observe(bar));

  // Contact form handling
  contactForm.addEventListener('submit', e => {
    e.preventDefault();

    const formData = new FormData(contactForm);
    const name = formData.get('name').trim();
    const email = formData.get('email').trim();
    const subject = formData.get('subject').trim();
    const message = formData.get('message').trim();

    if (!name || !email || !subject || !message) {
      showStatus('Please fill in all fields.', 'error');
      return;
    }

    if (!isValidEmail(email)) {
      showStatus('Please enter a valid email address.', 'error');
      return;
    }

    const submitBtn = contactForm.querySelector('button[type="submit"]');
    submitBtn.disabled = true;
    submitBtn.textContent = 'Sending...';

    setTimeout(() => {
      showStatus('Message sent successfully! I\'ll get back to you soon.', 'success');
      contactForm.reset();
      submitBtn.disabled = false;
      submitBtn.textContent = 'Send Message';
    }, 1500);
  });

  function showStatus(message, type) {
    formStatus.textContent = message;
    formStatus.className = `form__status ${type}`;
  }

  function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  }
});
