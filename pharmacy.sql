/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50128
Source Host           : localhost:3306
Source Database       : pharmacy

Target Server Type    : MYSQL
Target Server Version : 50128
File Encoding         : 65001

Date: 2020-12-25 14:08:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for drugs
-- ----------------------------
DROP TABLE IF EXISTS `drugs`;
CREATE TABLE `drugs` (
  `国药准字` varchar(20) DEFAULT NULL,
  `药品名` varchar(20) DEFAULT NULL,
  `治疗疾病科` varchar(20) DEFAULT NULL,
  `治疗症状` varchar(100) DEFAULT NULL,
  `购置数量` varchar(20) DEFAULT NULL,
  `进货人员` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of drugs
-- ----------------------------
INSERT INTO `drugs` VALUES ('Z43020334', '999感冒灵', '呼吸内科', '功效与作用：辛凉解表，清热解毒。用于风热感冒之发热，微恶风寒，头身痛，口干而渴，鼻塞涕浊，咽喉红肿疼痛，咳嗽，痰黄粘稠。 ', '30', '小明');
INSERT INTO `drugs` VALUES ('Z20000097', '龙心 芪龙胶囊', '心脑血管', '益气活血、化瘀通络。', '50', '小明');
INSERT INTO `drugs` VALUES ('591589452', '甲亢灵片', '神经科', '平肝潜阳，软坚散结。用于具有心悸、汗多、烦躁易怒、咽干、脉数等症状的甲状腺机能亢进症。', '66', '小王');
INSERT INTO `drugs` VALUES ('425968345', '脑乐静颗粒', '精神科', '养心，健脑，安神。用于精神忧郁，易惊失眠，烦躁。', '63', '小王');
INSERT INTO `drugs` VALUES ('535252877', '左旋多巴片', '精神科', '用于儿童、青少年中屈光不正性弱视、屈光参差性弱视及斜视性弱视患者。', '50', '小明');
INSERT INTO `drugs` VALUES ('366425425', '通脉颗粒', '心脑血管', '益气养阴，活血通络。用于气阴两虚、瘀血阻络所致的胸痹者', '50', '小王');
INSERT INTO `drugs` VALUES ('465642464', '痛风舒片', '风湿骨病', '清热，利湿，解毒。用于湿热淤阻所致的痛风病。', '42', '小李');
INSERT INTO `drugs` VALUES ('797625214', '可乐定透皮贴片', '精神科', '适用于Tourette综合征(发声与多种运动联合抽动障碍)。', '69', '小李');
INSERT INTO `drugs` VALUES ('465976757', '抗栓胶囊', '风湿骨病', '活血化瘀，抗栓通脉。', '57', '小王');
INSERT INTO `drugs` VALUES ('846145742', '归芍活血胶囊', '风湿骨病', '补肾活血。用于股骨头缺血性坏死的早期病症的辅助治疗。', '50', '小李');
INSERT INTO `drugs` VALUES ('585925727', '二十五味驴血丸', '风湿骨病', '祛风、除湿、干黄水。用于关节炎、类风湿性关节炎、痛风等', '61', '小王');
INSERT INTO `drugs` VALUES ('369765428', '盐酸氟西汀胶囊', '精神科', '抑郁发作，强迫症，精神性贪食症，可作为心理治疗的补充，用于减少贪食和导泻行为。', '70', '小李');
INSERT INTO `drugs` VALUES ('976483454', '维A酸片', '皮肤疾病', '适用于痤疮、扁平苔癣、白斑、毛发红糠疹和面部糠疹等。可作为银屑病、鱼鳞病的辅助治疗。', '58', '小明');
INSERT INTO `drugs` VALUES ('794645473', '乌发丸', '皮肤疾病', '滋阴健脑，凉血乌发。用于青少年白发症。', '50', '小王');
INSERT INTO `drugs` VALUES ('484875857', '十味诃子丸', '肾病科', '清肾热，利尿。用于肾炎，腰膝酸痛，尿频或尿闭，血尿,尿道结石等。', '48', '小王');
INSERT INTO `drugs` VALUES ('454957215', '复方活脑舒胶囊', '精神科', '补气养血，健脑益智。用于健忘气血亏虚证，老年性痴呆症状的改善。', '50', '小李');

-- ----------------------------
-- Table structure for managers
-- ----------------------------
DROP TABLE IF EXISTS `managers`;
CREATE TABLE `managers` (
  `管理员工作号` varchar(20) DEFAULT NULL,
  `管理员姓名` varchar(20) DEFAULT NULL,
  `管理员性别` varchar(10) DEFAULT NULL,
  `联系电话` varchar(20) DEFAULT NULL,
  `所在办公室` varchar(20) DEFAULT NULL,
  `管理员登录密码` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of managers
-- ----------------------------
INSERT INTO `managers` VALUES ('001', '刘一', '男', '19845622248', '0001', '1234');
INSERT INTO `managers` VALUES ('002', '陈二', '男', '14565239874', '0001', '1234');
INSERT INTO `managers` VALUES ('003', '张三', '男', '15796587423', '0002', '1234');
INSERT INTO `managers` VALUES ('004', '李四', '女', '19856327852', '0002', '1234');
INSERT INTO `managers` VALUES ('005', '王五', '女', '19852647852', '0003', '1234');
INSERT INTO `managers` VALUES ('006', '赵六', '男', '18745685269', '0003', '1234');
INSERT INTO `managers` VALUES ('007', '孙七', '女', '19354652583', '0003', '1234');
INSERT INTO `managers` VALUES ('008', '周八', '女', '18747851365', '0007', '1234');
INSERT INTO `managers` VALUES ('009', '吴九', '男', '15447474896', '0007', '1234');
INSERT INTO `managers` VALUES ('010', '郑十', '女', '18745874124', '0007', '1234');

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales` (
  `国药准字` varchar(20) DEFAULT NULL,
  `药品名` varchar(20) DEFAULT NULL,
  `病人姓名` varchar(20) DEFAULT NULL,
  `病人症状` varchar(100) DEFAULT NULL,
  `买药日期` varchar(100) DEFAULT NULL,
  `开药医生姓名` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES ('Z43020334', '999感冒灵', '小明', '感冒，流鼻涕\n', '2020-12-13', '小王');
INSERT INTO `sales` VALUES ('976483454', '维A酸片', '李沁鈺', '银屑病', '2020-03-04', '刘一');
INSERT INTO `sales` VALUES ('369765428', '盐酸氟西汀胶囊', '蒋彬豪', '强迫症', '2020-05-07', '张三');
INSERT INTO `sales` VALUES ('425968345', '脑乐静颗粒', '蒋育涵', '精神忧郁', '2019-08-18', '赵二');
INSERT INTO `sales` VALUES ('454957215', '复方活脑舒胶囊', '田梦菡', '健忘', '2020-05-16', '王五');
INSERT INTO `sales` VALUES ('465642464', '痛风舒片', '尹玉涵', '痛风病', '2020-11-07', '刘一');
INSERT INTO `sales` VALUES ('794645473', '乌发丸', '尹芊思', '青少年白发症', '2020-10-21', '李四');
INSERT INTO `sales` VALUES ('484875857', '十味诃子丸', '李子辰', '尿频', '2020-08-26', '刘一');
INSERT INTO `sales` VALUES ('976483454', '维A酸片', '蒋若豪', '白斑', '2020-06-17', '王五');
INSERT INTO `sales` VALUES ('585925727', '二十五味驴血丸', '江诗韵', '关节炎', '2020-01-22', '王五');
INSERT INTO `sales` VALUES ('976483454', '维A酸片', '田紫菡', '银屑病', '2020-01-25', '赵二');
INSERT INTO `sales` VALUES ('425968345', '脑乐静颗粒', '江玥儿', '易惊失眠', '2020-12-22', '张三');
INSERT INTO `sales` VALUES ('591589452', '甲亢灵片', '蒋雨豪', '咽干', '2020-11-27', '李四');
INSERT INTO `sales` VALUES ('585925727', '二十五味驴血丸', '李佳阳', '类风湿性关节炎', '2020-12-24', '刘一');
INSERT INTO `sales` VALUES ('794645473', '乌发丸', '田若萱', '青少年白发症', '2020-04-09', '张三');
INSERT INTO `sales` VALUES ('454957215', '复方活脑舒胶囊', '蒋玥熙', '气血亏虚', '2020-09-18', '赵二');
INSERT INTO `sales` VALUES ('1212', '12212', '1221', '121', '121', '1212');
