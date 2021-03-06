USE [THB4]
GO
/****** Object:  Table [dbo].[BANGDIEM]    Script Date: 21-Nov-20 2:54:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BANGDIEM](
	[IDDiem] [int] IDENTITY(1,1) NOT NULL,
	[HoVaTen] [nvarchar](1000) NOT NULL,
	[MaSV] [nvarchar](1000) NOT NULL,
	[IDLop] [nvarchar](1000) NOT NULL,
	[DBT1] [decimal](18, 2) NOT NULL,
	[DBT2] [decimal](18, 2) NOT NULL,
	[DBT3] [decimal](18, 2) NOT NULL,
	[DGK] [decimal](18, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[IDDiem] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[BANGDIEM] ON 

INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (1, N'minh duc', N'n17at018', N'd17at', CAST(10.00 AS Decimal(18, 2)), CAST(9.50 AS Decimal(18, 2)), CAST(8.70 AS Decimal(18, 2)), CAST(9.40 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (2, N'Phan Đại', N'n17at013', N'd17at', CAST(3.00 AS Decimal(18, 2)), CAST(5.50 AS Decimal(18, 2)), CAST(2.75 AS Decimal(18, 2)), CAST(3.75 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (4, N'Pz9j', N'MTIz', N'Mg==', CAST(1.00 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)), CAST(1.00 AS Decimal(18, 2)), CAST(1.33 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (5, N'??i', N'013', N'132', CAST(2.00 AS Decimal(18, 2)), CAST(3.00 AS Decimal(18, 2)), CAST(4.00 AS Decimal(18, 2)), CAST(3.00 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (6, N'?�ng', N'017', N'3', CAST(1.00 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)), CAST(3.00 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (7, N'??c', N'02', N'23', CAST(1.00 AS Decimal(18, 2)), CAST(1.00 AS Decimal(18, 2)), CAST(1.00 AS Decimal(18, 2)), CAST(1.00 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (8, N'd', N'1', N'1', CAST(1.00 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)), CAST(3.00 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (9, N'd1', N'd1', N'2', CAST(2.00 AS Decimal(18, 2)), CAST(3.00 AS Decimal(18, 2)), CAST(4.00 AS Decimal(18, 2)), CAST(3.00 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (10, N'd2', N'3', N'1', CAST(5.00 AS Decimal(18, 2)), CAST(4.50 AS Decimal(18, 2)), CAST(2.00 AS Decimal(18, 2)), CAST(3.83 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (11, N'd3', N'1', N'3', CAST(1.00 AS Decimal(18, 2)), CAST(5.40 AS Decimal(18, 2)), CAST(8.00 AS Decimal(18, 2)), CAST(4.80 AS Decimal(18, 2)))
INSERT [dbo].[BANGDIEM] ([IDDiem], [HoVaTen], [MaSV], [IDLop], [DBT1], [DBT2], [DBT3], [DGK]) VALUES (12, N'd5', N'45', N'2', CAST(5.00 AS Decimal(18, 2)), CAST(6.00 AS Decimal(18, 2)), CAST(7.80 AS Decimal(18, 2)), CAST(6.27 AS Decimal(18, 2)))
SET IDENTITY_INSERT [dbo].[BANGDIEM] OFF
GO
