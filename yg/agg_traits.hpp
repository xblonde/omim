#pragma once

#include <agg_pixfmt_rgba.h>
#include <agg_pixfmt_rgb_packed.h>
#include <agg_renderer_scanline.h>
#include <agg_rasterizer_scanline_aa.h>
#include <agg_scanline_u.h>
#include <agg_ellipse.h>

#include "data_formats.hpp"

template <typename Traits>
struct AggTraits
{
};

template <>
struct AggTraits<yg::RGBA8Traits>
{
  typedef agg::pixfmt_rgba32 pixfmt_t;
};

template <>
struct AggTraits<yg::RGBA4Traits>
{
  typedef agg::pixfmt_rgb4444 pixfmt_t;
};
