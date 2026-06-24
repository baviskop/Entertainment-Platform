# Design System: BVK Video Management Platform
**Project ID:** `13653850004459473338`  
**Brand:** BVK Video  
**Language:** Bilingual (Vietnamese primary labels + English navigation/actions)  
**Theme Mode:** Dual-theme — Dark (primary) + Light (alternate)

---

## 1. Visual Theme & Atmosphere

### Existing Aesthetic (Before)
The current platform uses a **Material 3-inspired light theme** with a warm-neutral surface palette. The mood is *functional, corporate, and clean* — surfaces are opaque off-whites (`#fbf9f8`), cards use thin warm borders (`#e0c0b2`), and the sidebar is a solid FPT Blue (`#265daf`) block. Typography is reliable but unexpressive. The overall impression is *utilitarian and template-like* — it serves its purpose but doesn't create emotional resonance for a media/entertainment platform.

### Redesigned Aesthetic (After)
The new BVK Video platform adopts a **cinematic editorial atmosphere** with dual-theme support:

**Dark Theme (Primary):** Deep, ink-like charcoal surfaces with frosted glass panels floating above. Warm orange accent lighting creates a "control room" energy — like a premium streaming platform's backstage. Subtle animated light orbs in the background add life without distraction. Every card and panel uses glassmorphism: translucent surfaces with blurred backgrounds reveal a sense of depth and layering.

**Light Theme (Alternate):** Crisp, snow-white canvas with cream-frosted glass panels. The glassmorphism effect manifests as subtle white-on-white layering with soft shadows replacing the dark-mode glow. Orange accents remain vibrant; blue shifts slightly warmer. The mood is *bright, airy, and editorial* — like a high-end magazine layout.

**Core Principles:**
- *Depth through translucency, not shadow* — glass surfaces replace flat cards
- *Generous breathing room* — whitespace is a design element, not wasted space
- *Asymmetric bento grids* — varied card sizes create visual rhythm and hierarchy
- *Cinematic imagery* — video thumbnails are treated as hero artwork, not utility

---

## 2. Color Palette & Roles

### 2.1 Existing Token Extraction (From Tailwind Config)

The current project defines a comprehensive Material 3 palette. Here is the full token map extracted from the Tailwind config across all 7 screens:

| Token Name | Hex Value | Current Role |
|---|---|---|
| `primary` | `#a04100` | Primary brand actions (deep burnt orange) |
| `primary-container` | `#f26f21` | CTAs, active states, upload button (vivid FPT orange) |
| `on-primary` | `#ffffff` | Text on primary surfaces |
| `on-primary-container` | `#531e00` | Text on orange containers |
| `primary-fixed` | `#ffdbcc` | Light orange tint backgrounds |
| `primary-fixed-dim` | `#ffb693` | Slightly deeper orange tint |
| `secondary` | `#265daf` | Navigation bar background, links (FPT blue) |
| `secondary-container` | `#7aa8ff` | Active sidebar item background |
| `on-secondary` | `#ffffff` | Text on blue surfaces |
| `on-secondary-container` | `#003b80` | Text on blue containers |
| `secondary-fixed` | `#d7e2ff` | Light blue tint |
| `secondary-fixed-dim` | `#acc7ff` | Medium blue tint |
| `tertiary` | `#006491` | Teal accent for secondary info |
| `tertiary-container` | `#299fde` | Brighter teal for badges |
| `tertiary-fixed` | `#c9e6ff` | Light cyan tint |
| `surface` | `#fbf9f8` | Page background (warm off-white) |
| `surface-container-lowest` | `#ffffff` | Pure white card backgrounds |
| `surface-container-low` | `#f5f3f3` | Input backgrounds, sidebar bg |
| `surface-container` | `#efeded` | Mid-tone surface |
| `surface-container-high` | `#e9e8e7` | Hover states, elevated surfaces |
| `surface-container-highest` | `#e4e2e2` | Footer background, highest elevation |
| `surface-dim` | `#dbdad9` | Scrollbar thumb, disabled surfaces |
| `on-surface` | `#1b1c1c` | Primary text color |
| `on-surface-variant` | `#584238` | Secondary text, labels (warm brown-gray) |
| `outline` | `#8c7166` | Borders, disabled icons (warm mid-tone) |
| `outline-variant` | `#e0c0b2` | Card borders, dividers (warm peach-gray) |
| `error` | `#ba1a1a` | Error states |
| `error-container` | `#ffdad6` | Error background |
| `inverse-surface` | `#303031` | Dark surface for tooltips/snackbars |
| `inverse-on-surface` | `#f2f0f0` | Text on dark inverse surfaces |
| `inverse-primary` | `#ffb693` | Primary on dark surfaces |

### 2.2 Redesigned Token Map — Dark Theme

| Descriptive Name | Hex Value | Role |
|---|---|---|
| **Void Canvas** | `#0F0F12` | Page background — the deepest layer, near-black with a hint of blue |
| **Frosted Glass** | `rgba(255,255,255,0.05)` | Default glass surface — cards, panels, sidebar |
| **Frosted Glass Elevated** | `rgba(255,255,255,0.08)` | Elevated glass — modals, popovers, dropdowns |
| **Frosted Glass Hover** | `rgba(255,255,255,0.10)` | Hover state for glass surfaces |
| **Ember Orange** (primary) | `#F26F21` | Primary CTA buttons, active indicators, progress bars, avatar rings |
| **Ember Orange Glow** | `rgba(242,111,33,0.20)` | Button glow shadow, focus rings, ambient light orbs |
| **Ember Orange Dim** | `#CC5A1A` | Pressed/active state for orange buttons |
| **Sapphire Blue** (secondary) | `#1250A2` | Links, informational badges, secondary buttons, chart lines |
| **Sapphire Blue Glass** | `rgba(18,80,162,0.15)` | Blue-tinted glass for informational cards |
| **Arctic Cyan** (tertiary) | `#38BDF8` | Tags, live indicators, sparkline charts, hashtags |
| **Snow White** | `#F5F5F7` | Primary text on dark surfaces — headings, titles |
| **Cool Ash** | `#A1A1AA` | Secondary text — body copy, metadata, view counts |
| **Dim Stone** | `#52525B` | Tertiary text — placeholders, disabled labels |
| **Ghost Border** | `rgba(255,255,255,0.06)` | Card borders, dividers, table grid lines |
| **Ghost Border Active** | `rgba(255,255,255,0.12)` | Focus rings, selected card borders |
| **Emerald Signal** | `#10B981` | Success state — "Công khai" status, verified badges |
| **Amber Signal** | `#F59E0B` | Warning state — "Đang chờ duyệt" badges |
| **Coral Alert** | `#EF4444` | Danger state — delete actions, error messages |
| **Neutral Slate** | `#71717A` | Neutral state — "Bản nháp" badges, disabled toggles |

### 2.3 Redesigned Token Map — Light Theme

| Descriptive Name | Hex Value | Role |
|---|---|---|
| **Cloud Canvas** | `#FAFAFA` | Page background — crisp near-white |
| **Cream Glass** | `rgba(255,255,255,0.70)` | Default glass surface with subtle translucency |
| **Cream Glass Elevated** | `rgba(255,255,255,0.85)` | Elevated glass — modals, popovers |
| **Cream Glass Hover** | `rgba(255,255,255,0.90)` | Hover state |
| **Ember Orange** (primary) | `#F26F21` | Same primary orange — consistent across themes |
| **Ember Orange Light** | `rgba(242,111,33,0.10)` | Subtle orange tint for hover backgrounds |
| **Sapphire Blue** (secondary) | `#1250A2` | Same blue — links, info elements |
| **Sapphire Blue Light** | `rgba(18,80,162,0.08)` | Blue tint for informational surfaces |
| **Ink Black** | `#1B1C1C` | Primary text — headings (same as existing `on-surface`) |
| **Slate Gray** | `#584238` | Secondary text — reuses existing `on-surface-variant` |
| **Warm Border** | `rgba(0,0,0,0.06)` | Card borders, dividers |
| **Warm Border Active** | `rgba(0,0,0,0.12)` | Focus rings, selected states |
| **Soft Shadow** | `0 4px 24px rgba(0,0,0,0.06)` | Light-theme elevation (replaces glow) |

---

## 3. Typography Rules

### Existing Type System
| Token | Family | Size | Weight | Line Height | Letter Spacing | Role |
|---|---|---|---|---|---|---|
| `headline-xl` | Hanken Grotesk | 48px | 700 | 56px | -0.02em | Page titles, hero headlines |
| `headline-lg` | Hanken Grotesk | 32px | 600 | 40px | -0.01em | Section headers, modal titles |
| `headline-lg-mobile` | Hanken Grotesk | 24px | 600 | 32px | — | Card titles, mobile headlines |
| `body-lg` | Inter | 18px | 400 | 28px | — | Channel names, prominent body |
| `body-md` | Inter | 16px | 400 | 24px | — | Default body text, descriptions |
| `label-sm` | Geist | 12px | 500 | 16px | 0.05em | Labels, badges, metadata, nav items |

### Redesigned Type Additions
The existing type scale is well-structured. The following additions expand it for the new design:

| Token | Family | Size | Weight | Line Height | Letter Spacing | New Role |
|---|---|---|---|---|---|---|
| `display` | Hanken Grotesk | 64px | 800 | 72px | -0.03em | Hero feature title on home page |
| `headline-md` | Hanken Grotesk | 20px | 600 | 28px | -0.005em | Bento card titles, stat labels |
| `body-sm` | Inter | 14px | 400 | 20px | — | Compact body text in sidebars |
| `label-xs` | Geist | 10px | 500 | 14px | 0.08em | Duration stamps, source badges |
| `mono` | Geist Mono | 13px | 400 | 18px | 0.02em | Video IDs, code references |

### Typographic Character
- **Headings** use **Hanken Grotesk** — geometric, confident, with tight negative tracking for a cinematic poster feel
- **Body text** uses **Inter** — neutral, highly legible, comfortable for long descriptions
- **Labels & data** use **Geist** — technical, precise, with positive tracking for scanability at small sizes
- On dark theme: headings render in **Snow White** (#F5F5F7), body in **Cool Ash** (#A1A1AA)
- On light theme: headings render in **Ink Black** (#1B1C1C), body in **Slate Gray** (#584238)

---

## 4. Component Stylings

### 4.1 Buttons

| Type | Shape | Background | Text | Border | Behavior |
|---|---|---|---|---|---|
| **Primary CTA** | Generously rounded (12px) | Solid Ember Orange (#F26F21) | Snow White, bold | None | Subtle orange glow shadow; scale(0.98) on press; hover brightens to #FF7F35 |
| **Primary Pill** | Fully rounded (pill-shaped) | Solid Ember Orange | Snow White, bold | None | Same as CTA but pill shape — used for Upload, Subscribe |
| **Secondary** | Generously rounded (12px) | Frosted glass (white-8%) | Snow White | Ghost Border (white-6%) | Hover reveals white-10% bg; scale(0.98) on press |
| **Ghost** | Generously rounded (12px) | Transparent | Cool Ash | None | Hover reveals frosted glass bg; text brightens to Snow White |
| **Danger** | Generously rounded (12px) | Transparent | Coral Alert | None | Hover reveals rgba(239,68,68,0.10) bg |
| **Icon Button** | Circular (full round) | Transparent | Cool Ash icon | None | Hover reveals frosted glass circle; icon brightens |

### 4.2 Cards & Containers

| Type | Corners | Background | Border | Depth | Notes |
|---|---|---|---|---|---|
| **Bento Card (standard)** | Smoothly rounded (20px) | Frosted Glass (white-5%) | Ghost Border (white-6%), 1px | Inner top-edge highlight: `inset 0 1px 0 rgba(255,255,255,0.05)` | Used for stats, video cards, form sections |
| **Bento Card (featured)** | Smoothly rounded (24px) | Frosted Glass Elevated (white-8%) | Ghost Border Active (white-12%), 1px | Stronger inner highlight + subtle colored glow when hovered | Hero video card, primary stat card |
| **Modal Overlay** | Smoothly rounded (24px) | Frosted Glass Elevated (white-8%) with blur(60px) | Ghost Border Active | Full-viewport backdrop: `rgba(0,0,0,0.60)` with blur(8px) | Forms, confirmations |
| **Sidebar Panel** | No radius (flush left) | Frosted Glass (white-5%) | Right edge Ghost Border | Flush to viewport edge, fixed position | Navigation |
| **Data Table** | Smoothly rounded (20px) outer | Frosted Glass (white-5%) | Ghost Border | Row hover: white-4% bg shift | Admin video list |
| **Auth Card** | Smoothly rounded (20px) | Frosted Glass Elevated (white-6%) with blur(60px) | Ghost Border Active (white-8%) | Orange glow underneath: `0 20px 60px rgba(242,111,33,0.08)` | Login, Register forms |

**Light theme adaptation:** Glass surfaces become `rgba(255,255,255,0.70)` with `blur(20px)`. Borders shift to `rgba(0,0,0,0.06)`. Depth uses soft drop shadows instead of inner glows.

### 4.3 Inputs & Forms

| Element | Background | Border | Focus State | Notes |
|---|---|---|---|---|
| **Text Input** | Frosted glass (white-4%) | Ghost Border (white-6%), 2px | Border transitions to Ember Orange; bg brightens to white-8%; icon tints orange | All inputs have left icon (Material Symbols) |
| **Select Dropdown** | Same as text input | Same | Same | Custom arrow icon; dropdown panel is frosted glass |
| **Textarea** | Same as text input | Same | Same | `resize: none`; height set by `rows` attribute |
| **Checkbox** | Transparent | Ghost Border, 2px, rounded-sm | Orange fill with white check icon | Custom styled to match glass aesthetic |
| **Toggle Switch** | Dim Stone (#52525B) track | None | Orange (#F26F21) track when checked; white circle thumb | Used in Settings for notification/security toggles |
| **Search Bar** | Frosted glass (white-6%), pill-shaped | Ghost Border | Expands width on focus; border transitions to orange | Centered in nav bar with category dropdown |

### 4.4 Status Badges

| Status | Background | Text | Shape |
|---|---|---|---|
| **Công khai (Public)** | `rgba(16,185,129,0.15)` | Emerald Signal (#10B981) | Pill-shaped, small |
| **Đang chờ duyệt (Pending)** | `rgba(245,158,11,0.15)` | Amber Signal (#F59E0B) | Pill-shaped, small |
| **Bản nháp (Draft)** | `rgba(113,113,122,0.15)` | Neutral Slate (#71717A) | Pill-shaped, small |
| **Hệ thống (System)** | Sapphire Blue Glass | Sapphire Blue (#1250A2) | Pill-shaped, small |
| **Youtube** | `rgba(239,68,68,0.10)` | Coral Alert (#EF4444) | Pill-shaped, small |
| **Verified** | None | Sapphire Blue | Icon only (check_circle, filled) |

### 4.5 Navigation

| Component | Dark Theme | Light Theme |
|---|---|---|
| **Top Nav Bar** | Frosted glass (blur 60px, white-5% bg), fixed top, 64px height | Cream Glass (blur 40px, white-70% bg), subtle bottom shadow |
| **Sidebar** | Frosted glass (white-5%), fixed left, 256px width | Same but cream-tinted |
| **Active Nav Item** | Orange left border (3px) + glass highlight (white-8%) + Snow White text | Orange left border + orange-tinted bg (orange-10%) + Ink Black text |
| **Inactive Nav Item** | Cool Ash text, transparent bg | Slate Gray text, transparent bg |
| **Nav Hover** | Frosted glass hover (white-10%) | Warm hover (black-4%) |
| **Mobile Bottom Bar** | Frosted glass (blur 40px), fixed bottom, 64px | Same, cream-tinted |

---

## 5. Layout Principles

### 5.1 Bento Grid System

The core layout philosophy is **asymmetric bento-box grids** — content blocks of varying sizes create visual rhythm and hierarchy, unlike uniform card grids.

**Base Grid:** 12-column CSS Grid with 24px gutters, max-width 1440px, centered.

**Bento Patterns:**

```
Hero Bento (Home):
┌──────────────────────┬───────────┐
│                      │           │
│   Featured (2×2)     │  Med (1×1)│
│                      │           │
│                      ├───────────┤
│                      │           │
│                      │  Med (1×1)│
└──────┬───────┬───────┴───────────┘
│ Small │ Small │ Small │ Small    │
└───────┴───────┴───────┴──────────┘
```

**Hero Bento (Home page):**
```css
.bento-hero {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: 280px;
  gap: 24px;
}
.bento-featured { grid-column: span 2; grid-row: span 2; }
```

**Stats Bento (Dashboard):**
```css
.bento-stats {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 24px;
}
```

**Profile Bento:**
```css
.bento-profile {
  display: grid;
  grid-template-columns: 4fr 8fr;
  gap: 24px;
}
```

### 5.2 Spacing Philosophy

The existing design uses tight `24px` gaps everywhere. The redesign introduces a **breathing hierarchy**:

| Scale Token | Value | Usage |
|---|---|---|
| `space-xs` | 4px | Inline icon gaps, badge padding-y |
| `space-sm` | 8px | Tight gaps within components (icon + text) |
| `space-md` | 16px | Internal card padding, form field gaps |
| `space-lg` | 24px | Bento grid gutters, section inner padding |
| `space-xl` | 32px | Between bento sections, major content blocks |
| `space-2xl` | 48px | Page section separators, hero margins |
| `space-3xl` | 64px | Desktop side margins, footer top padding |

**Key spacing rules:**
- Bento cell internal padding: `24px` (same as gutter for visual harmony)
- Between major page sections: `48px` minimum
- Desktop side margins: `64px` (existing `margin-desktop` preserved)
- Mobile side margins: `16px` (existing `margin-mobile` preserved)

### 5.3 Border Radius Scale

| Token | Value | Usage |
|---|---|---|
| `radius-sm` | 6px | Checkboxes, small badges |
| `radius-md` | 8px | Input fields, table cells |
| `radius-lg` | 12px | Buttons, dropdown panels |
| `radius-xl` | 16px | Small cards, thumbnails |
| `radius-2xl` | 20px | Standard bento cards, data table container |
| `radius-3xl` | 24px | Featured cards, modals, auth cards |
| `radius-full` | 9999px | Avatars, pill buttons, chips, search bar |

### 5.4 Breakpoints

| Breakpoint | Width | Layout Adaptation |
|---|---|---|
| `mobile` | < 640px | Single column, bottom nav bar, collapsed sidebar |
| `tablet` | 640–1024px | 2-column bento, top nav, no sidebar |
| `desktop` | 1024–1440px | Full bento grids, sidebar visible, side margins |
| `wide` | > 1440px | Centered with max-width, extra breathing room |

---

## 6. Glassmorphism Technical Specification

### 6.1 Glass Surface Recipes

```css
/* ===== DARK THEME ===== */
.glass {
  background: rgba(255, 255, 255, 0.05);
  backdrop-filter: blur(40px) saturate(180%);
  -webkit-backdrop-filter: blur(40px) saturate(180%);
  border: 1px solid rgba(255, 255, 255, 0.06);
  box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.05);
}

.glass-elevated {
  background: rgba(255, 255, 255, 0.08);
  backdrop-filter: blur(60px) saturate(200%);
  -webkit-backdrop-filter: blur(60px) saturate(200%);
  border: 1px solid rgba(255, 255, 255, 0.10);
  box-shadow: inset 0 1px 0 0 rgba(255, 255, 255, 0.08);
}

.glass-nav {
  background: rgba(15, 15, 18, 0.80);
  backdrop-filter: blur(60px) saturate(180%);
  border-bottom: 1px solid rgba(255, 255, 255, 0.06);
}

/* ===== LIGHT THEME ===== */
[data-theme="light"] .glass {
  background: rgba(255, 255, 255, 0.70);
  backdrop-filter: blur(20px) saturate(120%);
  border: 1px solid rgba(0, 0, 0, 0.06);
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.04);
}

[data-theme="light"] .glass-elevated {
  background: rgba(255, 255, 255, 0.85);
  backdrop-filter: blur(40px) saturate(150%);
  border: 1px solid rgba(0, 0, 0, 0.08);
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.06);
}

[data-theme="light"] .glass-nav {
  background: rgba(250, 250, 250, 0.85);
  backdrop-filter: blur(40px) saturate(150%);
  border-bottom: 1px solid rgba(0, 0, 0, 0.06);
}
```

### 6.2 Atmospheric Background

```css
/* Dark theme atmospheric layer */
.atmosphere {
  position: fixed;
  inset: 0;
  z-index: -1;
  background: #0F0F12;
  overflow: hidden;
}

.atmosphere::before {
  content: '';
  position: absolute;
  width: 600px;
  height: 600px;
  background: radial-gradient(circle, rgba(242,111,33,0.12) 0%, transparent 70%);
  top: -15%;
  right: -5%;
  border-radius: 50%;
  animation: drift 25s ease-in-out infinite;
}

.atmosphere::after {
  content: '';
  position: absolute;
  width: 500px;
  height: 500px;
  background: radial-gradient(circle, rgba(18,80,162,0.08) 0%, transparent 70%);
  bottom: -10%;
  left: -5%;
  border-radius: 50%;
  animation: drift 30s ease-in-out infinite reverse;
}

@keyframes drift {
  0%, 100% { transform: translate(0, 0) scale(1); }
  33% { transform: translate(30px, -20px) scale(1.05); }
  66% { transform: translate(-20px, 15px) scale(0.95); }
}

/* Light theme — subtle gradient, no animated orbs */
[data-theme="light"] .atmosphere {
  background: linear-gradient(135deg, #FAFAFA 0%, #FFF5F0 50%, #F0F4FF 100%);
}
[data-theme="light"] .atmosphere::before,
[data-theme="light"] .atmosphere::after {
  display: none;
}
```

### 6.3 Animation & Motion

| Property | Curve | Duration | Usage |
|---|---|---|---|
| **Hover transitions** | `cubic-bezier(0.4, 0, 0.2, 1)` | 200ms | Color, bg, border, opacity changes |
| **Scale press** | `cubic-bezier(0.4, 0, 0.2, 1)` | 100ms | Button press — `scale(0.98)` |
| **Glass reveal** | `cubic-bezier(0.0, 0, 0.2, 1)` | 300ms | Panels appearing, modals opening |
| **Atmospheric drift** | `ease-in-out` | 25–30s | Background light orb movement |
| **Play overlay** | `cubic-bezier(0.4, 0, 0.2, 1)` | 250ms | Opacity 0→1, scale 0.9→1.0 |
| **Sidebar expand** | `cubic-bezier(0.4, 0, 0.2, 1)` | 300ms | Width 64px → 256px |

---

## 7. Screen-by-Screen Token Application

| Screen | Key Layout | Primary Surface | Accent Usage | Special Elements |
|---|---|---|---|---|
| **Trang Chủ** (Home) | 4-col asymmetric bento grid | Glass cards with video thumbnails | Orange play overlays, blue category chips | Hero featured card (2×2), atmospheric bg |
| **Bảng Điều Khiển** (Admin) | Stats bento (3-col) + glass data table | Glass sidebar + glass table | Orange active nav, green/amber/gray badges | Modal form with glass overlay |
| **Chi Tiết Video** (Detail) | 70/30 split (player + sidebar) | Glass metadata panel below player | Orange like button, blue subscribe | Cinema player with glass controls bar |
| **Đăng Nhập** (Login) | Centered auth card | Glass card on atmospheric bg | Orange login button, blue links | Animated light orbs behind card |
| **Đăng Ký** (Register) | Centered auth card (wider) | Glass card with upload zone | Orange register pill, blue sign-in link | Dashed upload zone with glass border |
| **Hồ Sơ** (Profile) | 4+8 col bento split | Glass identity card + glass form card | Orange avatar ring, orange save button | Camera overlay on avatar hover |
| **Cài Đặt** (Settings) | Sidebar tabs + content area | Glass tab panel + glass content cards | Orange active tab, orange toggles | Theme selector cards (light/dark/system) |

---

## 8. Theme Switching Implementation

```html
<!-- Theme is controlled by data attribute on <html> -->
<html data-theme="dark" lang="vi">
```

**Tailwind integration:**
```javascript
tailwind.config = {
  darkMode: ['selector', '[data-theme="dark"]'],
  theme: {
    extend: {
      colors: {
        // Semantic tokens that adapt per theme
        'canvas':        'var(--color-canvas)',
        'glass':         'var(--color-glass)',
        'glass-border':  'var(--color-glass-border)',
        'text-primary':  'var(--color-text-primary)',
        'text-secondary':'var(--color-text-secondary)',
        // Static brand tokens
        'brand-orange':  '#F26F21',
        'brand-blue':    '#1250A2',
        'brand-cyan':    '#38BDF8',
      }
    }
  }
}
```

**CSS Custom Properties:**
```css
[data-theme="dark"] {
  --color-canvas: #0F0F12;
  --color-glass: rgba(255,255,255,0.05);
  --color-glass-border: rgba(255,255,255,0.06);
  --color-text-primary: #F5F5F7;
  --color-text-secondary: #A1A1AA;
}

[data-theme="light"] {
  --color-canvas: #FAFAFA;
  --color-glass: rgba(255,255,255,0.70);
  --color-glass-border: rgba(0,0,0,0.06);
  --color-text-primary: #1B1C1C;
  --color-text-secondary: #584238;
}
```
